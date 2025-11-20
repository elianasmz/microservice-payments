package com.cuidadodemascotas.microservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.cuidadodemascota.commons.dto.*;
import org.example.cuidadodemascota.commons.entities.invoice.Invoice;
import org.example.cuidadodemascota.commons.entities.reservation.Reservation;
import com.cuidadodemascotas.microservice.repository.InvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;

    public InvoiceService(InvoiceRepository invoiceRepository, ModelMapper modelMapper) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
    }
    @Transactional
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        log.info("Creating new invoice for reservation: {}", invoiceRequestDTO.getReservationId());

        Invoice invoice = new Invoice();
        invoice.setReservation(new Reservation());
        invoice.getReservation().setId(invoiceRequestDTO.getReservationId().longValue());
        invoice.setTotalAmount(BigDecimal.valueOf(invoiceRequestDTO.getTotalAmount()));
        invoice.setIssueDate(LocalDateTime.now());
        invoice.setActive(true);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        log.info("Invoice created successfully with ID: {}", savedInvoice.getId());
        return modelMapper.map(savedInvoice, InvoiceResponseDTO.class);
    }


    public InvoiceResponseDTO updateInvoice(Long id, InvoiceRequestDTO invoiceRequestDTO) {
        log.info("Updating invoice with ID: {}", id);

        Invoice existingInvoice = invoiceRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

        modelMapper.map(invoiceRequestDTO, existingInvoice);
        Invoice updatedInvoice = invoiceRepository.save(existingInvoice);

        log.info("Invoice updated successfully with ID: {}", id);
        return modelMapper.map(updatedInvoice, InvoiceResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public InvoiceResponseDTO getInvoiceById(Long id) {
        log.debug("Fetching invoice with ID: {}", id);

        Invoice invoice = invoiceRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

        return modelMapper.map(invoice, InvoiceResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public InvoiceResult getAllInvoices(Pageable pageable) {
        log.debug("Fetching all invoices with pagination");

        Page<Invoice> invoicePage = invoiceRepository.findByActiveTrue(pageable);
        return createInvoiceResult(invoicePage);
    }

    @Transactional(readOnly = true)
    public InvoiceResult searchInvoices(Long reservationId, Double minAmount, Double maxAmount,
                                        LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        log.debug("Searching invoices with filters");

        Page<Invoice> invoicePage = invoiceRepository.findWithFilters(
                reservationId, minAmount, maxAmount, startDate, endDate, pageable);

        return createInvoiceResult(invoicePage);
    }

    public void deleteInvoice(Long id) {
        log.info("Soft deleting invoice with ID: {}", id);

        Invoice invoice = invoiceRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

        invoice.setActive(false);
        invoiceRepository.save(invoice);

        log.info("Invoice soft deleted successfully with ID: {}", id);
    }

    private InvoiceResult createInvoiceResult(Page<Invoice> invoicePage) {
        List<InvoiceResponseDTO> items = invoicePage.getContent().stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceResponseDTO.class))
                .collect(Collectors.toList());

        InvoiceResult result = new InvoiceResult();
        result.setItems(items);
        result.setTotalCount((int) invoicePage.getTotalElements());

        return result;
    }
}
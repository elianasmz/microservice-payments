package com.cuidadodemascotas.microservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.cuidadodemascota.commons.dto.*;
        import org.example.cuidadodemascota.commons.entities.invoice.PaymentDetail;
import org.example.cuidadodemascota.commons.entities.invoice.Invoice;
import org.example.cuidadodemascota.commons.entities.invoice.PaymentMethod;
import com.cuidadodemascotas.microservice.repository.PaymentDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PaymentDetailService {

    private final PaymentDetailRepository paymentDetailRepository;
    private final ModelMapper modelMapper;

    public PaymentDetailService(PaymentDetailRepository paymentDetailRepository, ModelMapper modelMapper) {
        this.paymentDetailRepository = paymentDetailRepository;
        this.modelMapper = modelMapper;
    }

    public PaymentDetailResponseDTO createPaymentDetail(PaymentDetailRequestDTO requestDTO) {
        log.info("Creating new payment detail for invoice: {}", requestDTO.getInvoiceId());

        PaymentDetail paymentDetail = modelMapper.map(requestDTO, PaymentDetail.class);

        // Set invoice
        Invoice invoice = new Invoice();
        invoice.setId(requestDTO.getInvoiceId().longValue());
        paymentDetail.setInvoice(invoice);

        // Set payment method
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(requestDTO.getPaymentMethodId().longValue());
        paymentDetail.setPaymentMethod(paymentMethod);

        paymentDetail.setActive(true);
        PaymentDetail saved = paymentDetailRepository.save(paymentDetail);

        log.info("Payment detail created successfully with ID: {}", saved.getId());
        return modelMapper.map(saved, PaymentDetailResponseDTO.class);
    }

    public PaymentDetailResponseDTO updatePaymentDetail(Long id, PaymentDetailRequestDTO requestDTO) {
        log.info("Updating payment detail with ID: {}", id);

        PaymentDetail existing = paymentDetailRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Payment detail not found with id: " + id));

        modelMapper.map(requestDTO, existing);

        // Update relationships if provided
        if (requestDTO.getInvoiceId() != null) {
            Invoice invoice = new Invoice();
            invoice.setId(requestDTO.getInvoiceId().longValue());
            existing.setInvoice(invoice);
        }

        if (requestDTO.getPaymentMethodId() != null) {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setId(requestDTO.getPaymentMethodId().longValue());
            existing.setPaymentMethod(paymentMethod);
        }

        PaymentDetail updated = paymentDetailRepository.save(existing);

        log.info("Payment detail updated successfully with ID: {}", id);
        return modelMapper.map(updated, PaymentDetailResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public PaymentDetailResponseDTO getPaymentDetailById(Long id) {
        log.debug("Fetching payment detail with ID: {}", id);

        PaymentDetail paymentDetail = paymentDetailRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Payment detail not found with id: " + id));

        return modelMapper.map(paymentDetail, PaymentDetailResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public PaymentDetailResult getAllPaymentDetails(Pageable pageable) {
        log.debug("Fetching all payment details with pagination");

        Page<PaymentDetail> detailPage = paymentDetailRepository.findByActiveTrue(pageable);
        return createPaymentDetailResult(detailPage);
    }

    @Transactional(readOnly = true)
    public PaymentDetailResult searchPaymentDetails(Long invoiceId, Long paymentMethodId, Pageable pageable) {
        log.debug("Searching payment details with filters - invoiceId: {}, paymentMethodId: {}", invoiceId, paymentMethodId);

        Page<PaymentDetail> detailPage = paymentDetailRepository.findWithFilters(invoiceId, paymentMethodId, pageable);
        return createPaymentDetailResult(detailPage);
    }

    public void deletePaymentDetail(Long id) {
        log.info("Soft deleting payment detail with ID: {}", id);

        PaymentDetail paymentDetail = paymentDetailRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Payment detail not found with id: " + id));

        paymentDetail.setActive(false);
        paymentDetailRepository.save(paymentDetail);

        log.info("Payment detail soft deleted successfully with ID: {}", id);
    }

    private PaymentDetailResult createPaymentDetailResult(Page<PaymentDetail> detailPage) {
        List<PaymentDetailResponseDTO> items = detailPage.getContent().stream()
                .map(detail -> modelMapper.map(detail, PaymentDetailResponseDTO.class))
                .collect(Collectors.toList());

        PaymentDetailResult result = new PaymentDetailResult();
        result.setItems(items);
        result.setTotalCount((int) detailPage.getTotalElements());

        return result;
    }
}
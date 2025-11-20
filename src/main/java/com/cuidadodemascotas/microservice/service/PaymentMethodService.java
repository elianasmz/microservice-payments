package com.cuidadodemascotas.microservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.cuidadodemascota.commons.dto.*;
import org.example.cuidadodemascota.commons.entities.invoice.PaymentMethod;
import com.cuidadodemascotas.microservice.repository.PaymentMethodRepository;
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
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final ModelMapper modelMapper;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, ModelMapper modelMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelMapper = modelMapper;
    }

    public PaymentMethodResponseDTO createPaymentMethod(PaymentMethodRequestDTO requestDTO) {
        log.info("Creating new payment method: {}", requestDTO.getName());

        PaymentMethod paymentMethod = modelMapper.map(requestDTO, PaymentMethod.class);
        PaymentMethod saved = paymentMethodRepository.save(paymentMethod);

        log.info("Payment method created successfully with ID: {}", saved.getId());
        return modelMapper.map(saved, PaymentMethodResponseDTO.class);
    }

    public PaymentMethodResponseDTO findById(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado con id " + id));

        // Mapeo manual a DTO
        PaymentMethodResponseDTO dto = new PaymentMethodResponseDTO();
        dto.setId(paymentMethod.getId());
        dto.setName(paymentMethod.getName());
        return dto;
    }
    public PaymentMethodResponseDTO updatePaymentMethod(Long id, PaymentMethodRequestDTO requestDTO) {
        log.info("Updating payment method with ID: {}", id);

        PaymentMethod existing = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment method not found with id: " + id));

        modelMapper.map(requestDTO, existing);
        PaymentMethod updated = paymentMethodRepository.save(existing);

        log.info("Payment method updated successfully with ID: {}", id);
        return modelMapper.map(updated, PaymentMethodResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public PaymentMethodResponseDTO getPaymentMethodById(Long id) {
        log.debug("Fetching payment method with ID: {}", id);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment method not found with id: " + id));

        return modelMapper.map(paymentMethod, PaymentMethodResponseDTO.class);
    }

    public void deletePaymentMethod(Long id) {
        log.info("Soft deleting payment method with ID: {}", id);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment method not found with id: " + id));


        paymentMethodRepository.delete(paymentMethod);

        log.info("Payment method soft deleted successfully with ID: {}", id);
    }

    private PaymentMethodResult createPaymentMethodResult(Page<PaymentMethod> methodPage) {
        List<PaymentMethodResponseDTO> items = methodPage.getContent().stream()
                .map(method -> modelMapper.map(method, PaymentMethodResponseDTO.class))
                .collect(Collectors.toList());

        PaymentMethodResult result = new PaymentMethodResult();
        result.setItems(items);
        result.setTotalCount((int) methodPage.getTotalElements());

        return result;
    }

    /**
     * Obtener todos los métodos de pago con paginación
     */
    @Transactional(readOnly = true)
    public List<PaymentMethodResponseDTO> getAllPaymentMethods() {
        log.debug("Fetching all payment methods");

        List<PaymentMethod> methods = paymentMethodRepository.findAll();

        return methods.stream()
                .map(method -> modelMapper.map(method, PaymentMethodResponseDTO.class))
                .collect(Collectors.toList());
    }



}
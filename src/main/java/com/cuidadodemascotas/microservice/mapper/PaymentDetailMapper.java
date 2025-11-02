package com.cuidadodemascotas.microservice.mapper;

import org.example.cuidadodemascota.commons.dto.PaymentDetailRequestDTO;
import org.example.cuidadodemascota.commons.dto.PaymentDetailResponseDTO;
import org.example.cuidadodemascota.commons.entities.invoice.PaymentDetail;
import org.example.cuidadodemascota.commons.entities.invoice.Invoice;
import org.example.cuidadodemascota.commons.entities.invoice.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentDetailMapper {

    // Convierte de entidad a DTO (para enviar al frontend)
    public PaymentDetailResponseDTO toDTO(PaymentDetail entity) {
        if (entity == null) return null;

        PaymentDetailResponseDTO dto = new PaymentDetailResponseDTO();
        dto.setId(entity.getId());
        dto.setAppliedAmount(entity.getAppliedAmount().doubleValue());

        if (entity.getInvoice() != null) {
            dto.setInvoiceId((long) Math.toIntExact(entity.getInvoice().getId()));
        }
        if (entity.getPaymentMethod() != null) {
            dto.setPaymentMethodId((long) Math.toIntExact(entity.getPaymentMethod().getId()));
        }
        return dto;
    }

    // Convierte de DTO (request) a entidad (para guardar en BD)
    public PaymentDetail toEntity(PaymentDetailRequestDTO dto, Invoice invoice, PaymentMethod paymentMethod) {
        if (dto == null) return null;

        PaymentDetail entity = new PaymentDetail();
        entity.setInvoice(invoice);
        entity.setPaymentMethod(paymentMethod);
        entity.setAppliedAmount(BigDecimal.valueOf(dto.getAppliedAmount()));

        return entity;
    }
}

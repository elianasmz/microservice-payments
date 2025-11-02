package com.cuidadodemascotas.microservice.mapper;

import org.example.cuidadodemascota.commons.dto.PaymentMethodRequestDTO;
import org.example.cuidadodemascota.commons.dto.PaymentMethodResponseDTO;
import org.example.cuidadodemascota.commons.entities.invoice.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre PaymentMethod Entity y DTOs
 *
 * Convierte:
 * - Entity (del JAR entities) -> ResponseDTO (del JAR dtos)
 * - RequestDTO (del JAR dtos) -> Entity (del JAR entities)
 */
@Component
public class PaymentMethodMapper {

    private final ModelMapper modelMapper;

    public PaymentMethodMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Convierte de Entity a ResponseDTO
     *
     * @param entity PaymentMethod entity del JAR
     * @return PaymentMethodResponseDTO del JAR
     */
    public PaymentMethodResponseDTO toResponseDTO(PaymentMethod entity) {
        if (entity == null) {
            return null;
        }

        PaymentMethodResponseDTO dto = new PaymentMethodResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    /**
     * Convierte de RequestDTO a Entity
     *
     * @param dto PaymentMethodRequestDTO del JAR
     * @return PaymentMethod entity del JAR
     */
    public PaymentMethod toEntity(PaymentMethodRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        PaymentMethod entity = new PaymentMethod();
        entity.setName(dto.getName());

        return entity;
    }

    /**
     * Actualiza una entidad existente con datos del RequestDTO
     *
     * @param dto PaymentMethodRequestDTO con los nuevos datos
     * @param entity PaymentMethod entity existente a actualizar
     */
    public void updateEntityFromDTO(PaymentMethodRequestDTO dto, PaymentMethod entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getName() != null && !dto.getName().isEmpty()) {
            entity.setName(dto.getName());
        }
    }
}
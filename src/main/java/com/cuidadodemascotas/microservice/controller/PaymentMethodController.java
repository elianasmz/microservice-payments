package com.cuidadodemascotas.microservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cuidadodemascota.commons.dto.*;
import com.cuidadodemascotas.microservice.service.PaymentMethodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment-methods")
@Tag(name = "Payment Methods", description = "Operaciones relacionadas con los métodos de pago")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @Operation(summary = "Crear método de pago", description = "Crea un nuevo método de pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Método de pago creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PaymentMethodResponseDTO> createPaymentMethod(@RequestBody PaymentMethodRequestDTO requestDTO) {
        log.info("POST /api/v1/payment-methods - Creating new payment method");
        PaymentMethodResponseDTO response = paymentMethodService.createPaymentMethod(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Actualizar método de pago", description = "Actualiza un método de pago existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Método de pago actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
   // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodResponseDTO> updatePaymentMethod(
            @PathVariable Long id,
            @RequestBody PaymentMethodRequestDTO requestDTO) {
        log.info("PUT /api/v1/payment-methods/{} - Updating payment method", id);
        PaymentMethodResponseDTO response = paymentMethodService.updatePaymentMethod(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener método de pago por ID", description = "Devuelve la información de un método de pago específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Método de pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
  //  @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodResponseDTO> getPaymentMethodById(@PathVariable Long id) {
        log.info("GET /api/v1/payment-methods/{} - Fetching payment method by ID", id);
        PaymentMethodResponseDTO response = paymentMethodService.getPaymentMethodById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar método de pago", description = "Elimina un método de pago del sistema (borrado físico o lógico)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Método de pago eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado"),
            @ApiResponse(responseCode = "409", description = "No se puede eliminar porque está en uso")
    })
   // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Long id) {
        log.info("DELETE /api/v1/payment-methods/{} - Soft deleting payment method", id);
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}


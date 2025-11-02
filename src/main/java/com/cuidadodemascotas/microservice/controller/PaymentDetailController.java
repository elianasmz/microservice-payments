package com.cuidadodemascotas.microservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cuidadodemascota.commons.dto.*;
import com.cuidadodemascotas.microservice.service.PaymentDetailService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//Swagger/OpenAPI imports
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment-details")
@Tag(name = "Payment Details", description = "Operaciones relacionadas con los detalles de pago")
public class PaymentDetailController {

    private final PaymentDetailService paymentDetailService;

    public PaymentDetailController(PaymentDetailService paymentDetailService) {
        this.paymentDetailService = paymentDetailService;
    }

    //Crear detalle de pago
    @Operation(summary = "Crear detalle de pago", description = "Crea un nuevo detalle de pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle de pago creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
   // @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    public ResponseEntity<PaymentDetailResponseDTO> createPaymentDetail(@RequestBody PaymentDetailRequestDTO requestDTO) {
        log.info("POST /api/v1/payment-details - Creating new payment detail");
        PaymentDetailResponseDTO response = paymentDetailService.createPaymentDetail(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Actualizar detalle de pago
    @Operation(summary = "Actualizar detalle de pago", description = "Actualiza un detalle de pago existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de pago actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Detalle de pago no encontrado")
    })
    //@PreAuthorize("hasRole('OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDetailResponseDTO> updatePaymentDetail(
            @PathVariable Long id,
            @RequestBody PaymentDetailRequestDTO requestDTO) {
        log.info("PUT /api/v1/payment-details/{} - Updating payment detail", id);
        PaymentDetailResponseDTO response = paymentDetailService.updatePaymentDetail(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    //Buscar detalle de pago por ID
    @Operation(summary = "Obtener detalle de pago por ID", description = "Devuelve la información de un detalle de pago específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Detalle de pago no encontrado")
    })
   // @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetailResponseDTO> getPaymentDetailById(@PathVariable Long id) {
        log.info("GET /api/v1/payment-details/{} - Fetching payment detail by ID", id);
        PaymentDetailResponseDTO response = paymentDetailService.getPaymentDetailById(id);
        return ResponseEntity.ok(response);
    }

    //Listar todos los detalles de pago
    @Operation(summary = "Listar detalles de pago", description = "Obtiene una lista paginada de todos los detalles de pago")
    @ApiResponse(responseCode = "200", description = "Lista de detalles de pago obtenida exitosamente")
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PaymentDetailResult> getAllPaymentDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        log.info("GET /api/v1/payment-details - Fetching all payment details with pagination");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        PaymentDetailResult result = paymentDetailService.getAllPaymentDetails(pageable);
        return ResponseEntity.ok(result);
    }

    //Buscar con filtros
    @Operation(summary = "Buscar detalles de pago", description = "Permite buscar detalles de pago filtrando por factura o método de pago")
    @ApiResponse(responseCode = "200", description = "Resultados de búsqueda obtenidos exitosamente")
    //@PreAuthorize("hasRole('OWNER')")
    @GetMapping("/search")
    public ResponseEntity<PaymentDetailResult> searchPaymentDetails(
            @RequestParam(required = false) Long invoiceId,
            @RequestParam(required = false) Long paymentMethodId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("GET /api/v1/payment-details/search - Searching payment details with filters");
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        PaymentDetailResult result = paymentDetailService.searchPaymentDetails(invoiceId, paymentMethodId, pageable);
        return ResponseEntity.ok(result);
    }

    //Eliminar detalle de pago
    @Operation(summary = "Eliminar detalle de pago", description = "Elimina un detalle de pago del sistema (borrado lógico o físico según configuración)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Detalle de pago eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Detalle de pago no encontrado"),
            @ApiResponse(responseCode = "409", description = "No se puede eliminar porque está en uso")
    })
    //@PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentDetail(@PathVariable Long id) {
        log.info("DELETE /api/v1/payment-details/{} - Soft deleting payment detail", id);
        paymentDetailService.deletePaymentDetail(id);
        return ResponseEntity.noContent().build();
    }
}

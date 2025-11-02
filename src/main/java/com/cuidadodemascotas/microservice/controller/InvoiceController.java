package com.cuidadodemascotas.microservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cuidadodemascota.commons.dto.*;
import com.cuidadodemascotas.microservice.service.InvoiceService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.access.prepost.PreAuthorize;


@Slf4j
@RestController
@RequestMapping("/invoices")
@Tag(name = "Invoices", description = "Operaciones relacionadas con facturas")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    //Create
    @Operation(summary = "Crear una factura", description = "Crea una nueva factura en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factura creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
   // @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        log.info("POST /api/v1/invoices - Creating new invoice");
        InvoiceResponseDTO response = invoiceService.createInvoice(invoiceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Update
    @Operation(summary = "Actualizar una factura", description = "Actualiza los datos de una factura existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    //@PreAuthorize("hasRole('OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> updateInvoice(
            @PathVariable Long id,
            @RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        log.info("PUT /api/v1/invoices/{} - Updating invoice", id);
        InvoiceResponseDTO response = invoiceService.updateInvoice(id, invoiceRequestDTO);
        return ResponseEntity.ok(response);
    }

    // GetById
    @Operation(summary = "Obtener factura por ID", description = "Recupera los datos de una factura según su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
   // @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(@PathVariable Long id) {
        log.info("GET /api/v1/invoices/{} - Fetching invoice by ID", id);
        InvoiceResponseDTO response = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(response);
    }

    //GetAll
    @Operation(summary = "Listar todas las facturas", description = "Obtiene todas las facturas con paginación y ordenamiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida exitosamente")
    })
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<InvoiceResult> getAllInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        log.info("GET /api/v1/invoices - Fetching all invoices with pagination");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        InvoiceResult result = invoiceService.getAllInvoices(pageable);
        return ResponseEntity.ok(result);
    }

    // Search
    @Operation(summary = "Buscar facturas", description = "Permite filtrar facturas por reserva, montos y rango de fechas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de búsqueda inválidos")
    })
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<InvoiceResult> searchInvoices(
            @RequestParam(required = false) Long reservationId,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("GET /api/v1/invoices/search - Searching invoices with filters");
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        InvoiceResult result = invoiceService.searchInvoices(
                reservationId, minAmount, maxAmount, startDate, endDate, pageable);
        return ResponseEntity.ok(result);
    }

    //Delete
    @Operation(summary = "Eliminar factura", description = "Elimina (lógicamente) una factura del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Factura eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada"),
            @ApiResponse(responseCode = "409", description = "No se puede eliminar porque está en uso")
    })
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        log.info("DELETE /api/v1/invoices/{} - Soft deleting invoice", id);
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
   /* @GetMapping("search-my-invoice/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id) {
        return ResponseEntity.ok(InvoiceService.findByIdInvoice(id));
    }*/
}

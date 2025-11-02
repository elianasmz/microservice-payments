package com.cuidadodemascotas.microservice.repository;

import org.example.cuidadodemascota.commons.entities.invoice.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Page<Invoice> findByActiveTrue(Pageable pageable);

    Optional<Invoice> findByIdAndActiveTrue(Long id);

    @Query("SELECT i FROM Invoice i WHERE i.active = true AND " +
            "(:reservationId IS NULL OR i.reservation.id = :reservationId) AND " +
            "(:minAmount IS NULL OR i.totalAmount >= :minAmount) AND " +
            "(:maxAmount IS NULL OR i.totalAmount <= :maxAmount) AND " +
            "(:startDate IS NULL OR i.issueDate >= :startDate) AND " +
            "(:endDate IS NULL OR i.issueDate <= :endDate)")
    Page<Invoice> findWithFilters(@Param("reservationId") Long reservationId,
                                  @Param("minAmount") Double minAmount,
                                  @Param("maxAmount") Double maxAmount,
                                  @Param("startDate") LocalDateTime startDate,
                                  @Param("endDate") LocalDateTime endDate,
                                  Pageable pageable);
}
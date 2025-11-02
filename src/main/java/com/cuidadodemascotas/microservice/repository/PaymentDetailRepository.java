package com.cuidadodemascotas.microservice.repository;

import org.example.cuidadodemascota.commons.entities.invoice.PaymentDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {

    Page<PaymentDetail> findByActiveTrue(Pageable pageable);

    Optional<PaymentDetail> findByIdAndActiveTrue(Long id);

    @Query("SELECT pd FROM PaymentDetail pd WHERE pd.active = true AND " +
            "(:invoiceId IS NULL OR pd.invoice.id = :invoiceId) AND " +
            "(:paymentMethodId IS NULL OR pd.paymentMethod.id = :paymentMethodId)")
    Page<PaymentDetail> findWithFilters(@Param("invoiceId") Long invoiceId,
                                        @Param("paymentMethodId") Long paymentMethodId,
                                        Pageable pageable);
}
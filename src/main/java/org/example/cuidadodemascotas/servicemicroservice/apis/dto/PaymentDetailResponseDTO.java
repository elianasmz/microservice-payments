package org.example.cuidadodemascotas.servicemicroservice.apis.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PaymentDetailResponseDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-20T03:45:32.928054400-03:00[America/Asuncion]")
public class PaymentDetailResponseDTO {

  private Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  private Boolean active;

  private Long invoiceId;

  private Long paymentMethodId;

  private Double appliedAmount;

  public PaymentDetailResponseDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PaymentDetailResponseDTO createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @Valid 
  @Schema(name = "createdAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public PaymentDetailResponseDTO updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
  */
  @Valid 
  @Schema(name = "updatedAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public PaymentDetailResponseDTO active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  */
  
  @Schema(name = "active", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("active")
  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public PaymentDetailResponseDTO invoiceId(Long invoiceId) {
    this.invoiceId = invoiceId;
    return this;
  }

  /**
   * Get invoiceId
   * @return invoiceId
  */
  
  @Schema(name = "invoiceId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("invoiceId")
  public Long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(Long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public PaymentDetailResponseDTO paymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
    return this;
  }

  /**
   * Get paymentMethodId
   * @return paymentMethodId
  */
  
  @Schema(name = "paymentMethodId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("paymentMethodId")
  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  public PaymentDetailResponseDTO appliedAmount(Double appliedAmount) {
    this.appliedAmount = appliedAmount;
    return this;
  }

  /**
   * Get appliedAmount
   * @return appliedAmount
  */
  
  @Schema(name = "appliedAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("appliedAmount")
  public Double getAppliedAmount() {
    return appliedAmount;
  }

  public void setAppliedAmount(Double appliedAmount) {
    this.appliedAmount = appliedAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentDetailResponseDTO paymentDetailResponseDTO = (PaymentDetailResponseDTO) o;
    return Objects.equals(this.id, paymentDetailResponseDTO.id) &&
        Objects.equals(this.createdAt, paymentDetailResponseDTO.createdAt) &&
        Objects.equals(this.updatedAt, paymentDetailResponseDTO.updatedAt) &&
        Objects.equals(this.active, paymentDetailResponseDTO.active) &&
        Objects.equals(this.invoiceId, paymentDetailResponseDTO.invoiceId) &&
        Objects.equals(this.paymentMethodId, paymentDetailResponseDTO.paymentMethodId) &&
        Objects.equals(this.appliedAmount, paymentDetailResponseDTO.appliedAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, updatedAt, active, invoiceId, paymentMethodId, appliedAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentDetailResponseDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    invoiceId: ").append(toIndentedString(invoiceId)).append("\n");
    sb.append("    paymentMethodId: ").append(toIndentedString(paymentMethodId)).append("\n");
    sb.append("    appliedAmount: ").append(toIndentedString(appliedAmount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


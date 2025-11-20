package org.example.cuidadodemascotas.servicemicroservice.apis.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PaymentDetailRequestDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-20T03:45:32.928054400-03:00[America/Asuncion]")
public class PaymentDetailRequestDTO {

  private Long invoiceId;

  private Long paymentMethodId;

  private Double appliedAmount;

  public PaymentDetailRequestDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PaymentDetailRequestDTO(Long invoiceId, Long paymentMethodId, Double appliedAmount) {
    this.invoiceId = invoiceId;
    this.paymentMethodId = paymentMethodId;
    this.appliedAmount = appliedAmount;
  }

  public PaymentDetailRequestDTO invoiceId(Long invoiceId) {
    this.invoiceId = invoiceId;
    return this;
  }

  /**
   * Get invoiceId
   * @return invoiceId
  */
  @NotNull 
  @Schema(name = "invoiceId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("invoiceId")
  public Long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(Long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public PaymentDetailRequestDTO paymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
    return this;
  }

  /**
   * Get paymentMethodId
   * @return paymentMethodId
  */
  @NotNull 
  @Schema(name = "paymentMethodId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentMethodId")
  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(Long paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  public PaymentDetailRequestDTO appliedAmount(Double appliedAmount) {
    this.appliedAmount = appliedAmount;
    return this;
  }

  /**
   * Get appliedAmount
   * @return appliedAmount
  */
  @NotNull 
  @Schema(name = "appliedAmount", requiredMode = Schema.RequiredMode.REQUIRED)
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
    PaymentDetailRequestDTO paymentDetailRequestDTO = (PaymentDetailRequestDTO) o;
    return Objects.equals(this.invoiceId, paymentDetailRequestDTO.invoiceId) &&
        Objects.equals(this.paymentMethodId, paymentDetailRequestDTO.paymentMethodId) &&
        Objects.equals(this.appliedAmount, paymentDetailRequestDTO.appliedAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoiceId, paymentMethodId, appliedAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentDetailRequestDTO {\n");
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


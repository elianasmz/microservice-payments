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
 * InvoiceRequestDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-20T03:45:32.928054400-03:00[America/Asuncion]")
public class InvoiceRequestDTO {

  private Long reservationId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime issueDate;

  private Double totalAmount;

  public InvoiceRequestDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InvoiceRequestDTO(Long reservationId, Double totalAmount) {
    this.reservationId = reservationId;
    this.totalAmount = totalAmount;
  }

  public InvoiceRequestDTO reservationId(Long reservationId) {
    this.reservationId = reservationId;
    return this;
  }

  /**
   * Get reservationId
   * @return reservationId
  */
  @NotNull 
  @Schema(name = "reservationId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("reservationId")
  public Long getReservationId() {
    return reservationId;
  }

  public void setReservationId(Long reservationId) {
    this.reservationId = reservationId;
  }

  public InvoiceRequestDTO issueDate(OffsetDateTime issueDate) {
    this.issueDate = issueDate;
    return this;
  }

  /**
   * Get issueDate
   * @return issueDate
  */
  @Valid 
  @Schema(name = "issueDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("issueDate")
  public OffsetDateTime getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(OffsetDateTime issueDate) {
    this.issueDate = issueDate;
  }

  public InvoiceRequestDTO totalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
    return this;
  }

  /**
   * Get totalAmount
   * @return totalAmount
  */
  @NotNull 
  @Schema(name = "totalAmount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("totalAmount")
  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InvoiceRequestDTO invoiceRequestDTO = (InvoiceRequestDTO) o;
    return Objects.equals(this.reservationId, invoiceRequestDTO.reservationId) &&
        Objects.equals(this.issueDate, invoiceRequestDTO.issueDate) &&
        Objects.equals(this.totalAmount, invoiceRequestDTO.totalAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reservationId, issueDate, totalAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InvoiceRequestDTO {\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
    sb.append("    issueDate: ").append(toIndentedString(issueDate)).append("\n");
    sb.append("    totalAmount: ").append(toIndentedString(totalAmount)).append("\n");
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


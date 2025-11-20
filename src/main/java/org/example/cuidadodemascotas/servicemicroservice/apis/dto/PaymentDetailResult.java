package org.example.cuidadodemascotas.servicemicroservice.apis.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.cuidadodemascotas.servicemicroservice.apis.dto.PaymentDetailResponseDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PaymentDetailResult
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-20T03:45:32.928054400-03:00[America/Asuncion]")
public class PaymentDetailResult {

  private Integer totalCount;

  @Valid
  private List<@Valid PaymentDetailResponseDTO> items;

  public PaymentDetailResult totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Get totalCount
   * @return totalCount
  */
  
  @Schema(name = "totalCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalCount")
  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public PaymentDetailResult items(List<@Valid PaymentDetailResponseDTO> items) {
    this.items = items;
    return this;
  }

  public PaymentDetailResult addItemsItem(PaymentDetailResponseDTO itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @Valid 
  @Schema(name = "items", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("items")
  public List<@Valid PaymentDetailResponseDTO> getItems() {
    return items;
  }

  public void setItems(List<@Valid PaymentDetailResponseDTO> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentDetailResult paymentDetailResult = (PaymentDetailResult) o;
    return Objects.equals(this.totalCount, paymentDetailResult.totalCount) &&
        Objects.equals(this.items, paymentDetailResult.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentDetailResult {\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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


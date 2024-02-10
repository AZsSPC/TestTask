package com.illiapinchuk.testtask.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illiapinchuk.testtask.persistence.entity.Bid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/** Incoming DTO to represent {@link Bid}. */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BidDto {

  @NotNull Double amount;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  LocalDateTime bidTime;

  @NotNull Long auctionId;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  Long bidderId;
}

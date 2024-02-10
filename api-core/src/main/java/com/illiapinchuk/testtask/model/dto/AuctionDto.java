package com.illiapinchuk.testtask.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illiapinchuk.testtask.persistence.entity.AuctionPhoto;
import com.illiapinchuk.testtask.persistence.entity.Bid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** DTO class for auction request. */
@EqualsAndHashCode
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDto {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  Long id;

  @NotBlank String title;
  @NotBlank String description;
  @NotNull Double startPrice;
  LocalDateTime startTime;
  LocalDateTime endTime;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  Long creatorId;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  Set<Bid> bids;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  Set<AuctionPhoto> photos;
}

package com.illiapinchuk.testtask.model.dto;

import com.illiapinchuk.testtask.persistence.entity.Bid;
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

  Long id;
  String title;
  String description;
  Double startPrice;
  LocalDateTime startTime;
  LocalDateTime endTime;
  Set<Bid> bids;
}

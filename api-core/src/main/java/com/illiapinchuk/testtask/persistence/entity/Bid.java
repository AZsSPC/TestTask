package com.illiapinchuk.testtask.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Bid class represents a bid in the db. */
@Entity
@Table(name = "auctions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  Double amount;
  LocalDateTime bidTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "auction_id")
  Auction auction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bidder_id")
  User bidder;
}

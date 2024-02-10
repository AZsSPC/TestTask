package com.illiapinchuk.testtask.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
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
@Table(name = "bids")
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

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "auction_id")
  Auction auction;

  @ManyToOne
  @JoinColumn(name = "bidder_id")
  User bidder;
}

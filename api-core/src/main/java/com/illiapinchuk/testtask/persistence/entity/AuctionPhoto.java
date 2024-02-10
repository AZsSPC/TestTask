package com.illiapinchuk.testtask.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/** Auction photo class represents an auction photo in the db. */
@Entity
@Table(name = "auction_photos")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuctionPhoto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String url; // photo URL (path) that stores in S3

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "auction_id")
  Auction auction;
}

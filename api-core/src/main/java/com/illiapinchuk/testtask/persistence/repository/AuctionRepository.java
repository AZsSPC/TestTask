package com.illiapinchuk.testtask.persistence.repository;

import com.illiapinchuk.testtask.persistence.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

/** Basic repository for auction. */
public interface AuctionRepository extends JpaRepository<Auction, Long> {}

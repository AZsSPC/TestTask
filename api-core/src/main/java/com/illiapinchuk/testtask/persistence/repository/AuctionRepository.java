package com.illiapinchuk.testtask.persistence.repository;

import com.illiapinchuk.testtask.persistence.entity.Auction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Basic repository for auction. */
public interface AuctionRepository extends JpaRepository<Auction, Long> {}

package com.illiapinchuk.testtask.persistence.repository;

import com.illiapinchuk.testtask.persistence.entity.Bid;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository for managing {@link Bid}. */
public interface BidRepository extends JpaRepository<Bid, Long> {

  Bid findFirstByAuctionIdOrderByAmountDesc(Long auctionId);

  List<Bid> findAllByAuctionId(Long auctionId);
}

package com.illiapinchuk.testtask.service;

import com.illiapinchuk.testtask.model.dto.BidDto;
import com.illiapinchuk.testtask.persistence.entity.Bid;
import java.util.List;

/** Service interface for managing bids. */
public interface BidService {

  Bid createBid(BidDto bidDto);

  Bid getMaxBidByAuctionId(Long auctionId);

  List<Bid> getBidsByAuctionId(Long auctionId);
}

package com.illiapinchuk.testtask.service;

import com.illiapinchuk.testtask.model.dto.AuctionDto;
import com.illiapinchuk.testtask.persistence.entity.Auction;

/** Service interface for managing auctions. */
public interface AuctionService {

  Auction getAuctionById(Long id);

  Auction createAuction(Auction auction);

  Auction updateAuction(AuctionDto auctionDto);

  void deleteAuctionById(Long id);
}

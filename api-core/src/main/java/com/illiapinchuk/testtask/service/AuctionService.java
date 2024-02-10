package com.illiapinchuk.testtask.service;

import com.illiapinchuk.testtask.model.dto.AuctionDto;
import com.illiapinchuk.testtask.persistence.entity.Auction;
import org.springframework.data.domain.Page;

/** Service interface for managing auctions. */
public interface AuctionService {

  /**
   * Retrieves a paginated list of all auctions.
   *
   * @param page The page number (zero-based) of the results to retrieve.
   * @param size The size of each page.
   * @return A {@link Page} containing {@link AuctionDto} objects.
   */
  Page<AuctionDto> getAllAuctions(int page, int size);

  /**
   * Retrieves an auction by its unique identifier.
   *
   * @param id The unique identifier of the auction.
   * @return The {@link Auction} object if found, otherwise null.
   */
  Auction getAuctionById(Long id);

  /**
   * Creates a new auction.
   *
   * @param auction The {@link Auction} object representing the auction to be created.
   * @return The created {@link Auction} object.
   */
  Auction createAuction(Auction auction);

  /**
   * Updates an existing auction with the provided data.
   *
   * @param auctionDto The {@link AuctionDto} object containing updated data.
   * @return The updated {@link Auction} object.
   */
  Auction updateAuction(AuctionDto auctionDto);

  /**
   * Deletes an auction by its unique identifier.
   *
   * @param id The unique identifier of the auction to delete.
   */
  void deleteAuctionById(Long id);
}

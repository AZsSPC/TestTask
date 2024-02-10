package com.illiapinchuk.testtask.common.validator;

import com.illiapinchuk.testtask.persistence.repository.AuctionRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** Validation for auction-related information. */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuctionValidator {

  private final AuctionRepository auctionRepository;

  /**
   * Check if the given auction with id exists in the database.
   *
   * @param id the id to check
   * @return true if the auction exists in the database, false otherwise
   */
  public boolean isAuctionExistsInDbById(@Nonnull final Long id) {
    return auctionRepository.existsById(id);
  }

  /**
   * Checks if a specific user created a specific auction.
   *
   * @param auctionId The ID of the auction to check.
   * @param userId The ID of the user to check.
   * @return true if the user created the auction, false otherwise.
   */
  public boolean isUserCreatedAuction(@Nonnull final Long auctionId, @Nonnull final Long userId) {
    return auctionRepository.getReferenceById(auctionId).getCreator().getId().equals(userId);
  }
}

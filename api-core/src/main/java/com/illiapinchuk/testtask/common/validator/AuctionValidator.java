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
}

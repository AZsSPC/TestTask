package com.illiapinchuk.testtask.service.impl;

import com.illiapinchuk.testtask.common.mapper.AuctionMapper;
import com.illiapinchuk.testtask.common.validator.AuctionValidator;
import com.illiapinchuk.testtask.exception.AuctionAlreadyExistsException;
import com.illiapinchuk.testtask.exception.AuctionNotFoundException;
import com.illiapinchuk.testtask.exception.UserIsNotOwnerOfTheAuctionException;
import com.illiapinchuk.testtask.model.dto.AuctionDto;
import com.illiapinchuk.testtask.persistence.entity.Auction;
import com.illiapinchuk.testtask.persistence.repository.AuctionRepository;
import com.illiapinchuk.testtask.service.AuctionService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Implementation of {@link AuctionService} interface. */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuctionServiceImpl implements AuctionService {

  private final AuctionRepository auctionRepository;

  private final AuctionValidator auctionValidator;
  private final AuctionMapper auctionMapper;

  @Override
  @Transactional(readOnly = true)
  public Auction getAuctionById(@Nonnull final Long id) {
    return auctionRepository
        .findById(id)
        .orElseThrow(
            () -> new AuctionNotFoundException(String.format("Auction with id: %s not found", id)));
  }

  @Override
  @Transactional
  public Auction createAuction(@Nonnull final Auction auction) {
    if (auction.getId() != null && auctionValidator.isAuctionExistsInDbById(auction.getId()))
      throw new AuctionAlreadyExistsException(
          String.format("Auction with id: %s already exists", auction.getId()));

    return auctionRepository.save(auction);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Auction updateAuction(@Nonnull final AuctionDto auctionDto) {
    final var auctionId = auctionDto.getId();

    checkIfAuctionExists(auctionId);
    checkIfUserCreatedAuction(auctionId, auctionDto.getCreatorId());

    final var auction = getAuctionById(auctionId);

    auctionMapper.updateAuction(auction, auctionDto);

    return auctionRepository.save(auction);
  }

  @Override
  public void deleteAuctionById(@Nonnull final Long id) {
    auctionRepository.deleteById(id);
  }

  private void checkIfAuctionExists(Long auctionId) {
    if (!auctionValidator.isAuctionExistsInDbById(auctionId)) {
      throw new AuctionNotFoundException(String.format("Auction with id: %s not found", auctionId));
    }
  }

  private void checkIfUserCreatedAuction(Long auctionId, Long creatorId) {
    if (!auctionValidator.isUserCreatedAuction(auctionId, creatorId)) {
      throw new UserIsNotOwnerOfTheAuctionException(
          String.format(
              "User with id: %s did not create the auction with id: %s", creatorId, auctionId));
    }
  }
}

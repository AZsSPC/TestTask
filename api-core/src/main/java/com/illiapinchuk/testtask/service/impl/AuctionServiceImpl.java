package com.illiapinchuk.testtask.service.impl;

import com.illiapinchuk.testtask.common.mapper.AuctionMapper;
import com.illiapinchuk.testtask.common.validator.AuctionValidator;
import com.illiapinchuk.testtask.configuration.security.UserPermissionService;
import com.illiapinchuk.testtask.exception.AuctionAlreadyExistsException;
import com.illiapinchuk.testtask.exception.AuctionNotFoundException;
import com.illiapinchuk.testtask.exception.UserIsNotOwnerOfTheAuctionException;
import com.illiapinchuk.testtask.model.dto.AuctionDto;
import com.illiapinchuk.testtask.persistence.entity.Auction;
import com.illiapinchuk.testtask.persistence.repository.AuctionRepository;
import com.illiapinchuk.testtask.service.AuctionService;
import com.illiapinchuk.testtask.service.UserService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Implementation of {@link AuctionService} interface. */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuctionServiceImpl implements AuctionService {

  private final UserService userService;

  private final AuctionRepository auctionRepository;

  private final AuctionValidator auctionValidator;
  private final AuctionMapper auctionMapper;

  @Override
  @Transactional(readOnly = true)
  public Page<AuctionDto> getAllAuctions(int page, int size) {
    final var pageable = PageRequest.of(page, size);

    return auctionRepository.findAll(pageable).map(auctionMapper::auctionToAuctionDto);
  }

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
    final var currentUser = userService.getUserById(UserPermissionService.getJwtUser().getId());

    // Check if the auction already exists
    if (auction.getId() != null && auctionValidator.isAuctionExistsInDbById(auction.getId())) {
      throw new AuctionAlreadyExistsException(
          String.format("Auction with id: %s already exists", auction.getId()));
    }

    // Set the creator of the auction
    auction.setCreator(currentUser);

    return auctionRepository.save(auction);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Auction updateAuction(@Nonnull final AuctionDto auctionDto) {
    final var auctionId = auctionDto.getId();

    checkIfAuctionExists(auctionId);
    checkIfUserHaveAccessToAuction(auctionId, auctionDto.getCreatorId());

    final var auction = getAuctionById(auctionId);

    auctionMapper.updateAuction(auction, auctionDto);

    return auctionRepository.save(auction);
  }

  @Override
  public void deleteAuctionById(@Nonnull final Long id) {
    checkIfUserHaveAccessToAuction(id, UserPermissionService.getJwtUser().getId());

    auctionRepository.deleteById(id);
  }

  private void checkIfAuctionExists(@Nonnull final Long auctionId) {
    if (!auctionValidator.isAuctionExistsInDbById(auctionId)) {
      throw new AuctionNotFoundException(String.format("Auction with id: %s not found", auctionId));
    }
  }

  private void checkIfUserHaveAccessToAuction(
      @Nonnull final Long auctionId, @Nonnull final Long creatorId) {
    if (!auctionValidator.isUserCreatedAuction(auctionId, creatorId)
        && !UserPermissionService.hasAnyRulingRole()) {
      throw new UserIsNotOwnerOfTheAuctionException(
          String.format(
              "User with id: %s did not create the auction with id: %s", creatorId, auctionId));
    }
  }
}

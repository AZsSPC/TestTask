package com.illiapinchuk.testtask.service.impl;

import com.illiapinchuk.testtask.common.validator.AuctionValidator;
import com.illiapinchuk.testtask.configuration.security.UserPermissionService;
import com.illiapinchuk.testtask.exception.AuctionNotFoundException;
import com.illiapinchuk.testtask.exception.BidShouldBeMoreThenPreviousOne;
import com.illiapinchuk.testtask.model.dto.BidDto;
import com.illiapinchuk.testtask.persistence.entity.Bid;
import com.illiapinchuk.testtask.persistence.repository.BidRepository;
import com.illiapinchuk.testtask.service.AuctionService;
import com.illiapinchuk.testtask.service.BidService;
import com.illiapinchuk.testtask.service.UserService;
import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

  private final BidRepository bidRepository;
  private final AuctionService auctionService;
  private final UserService userService;
  private final AuctionValidator auctionValidator;

  @Override
  @Transactional
  public Bid createBid(@Nonnull final BidDto bidDto) {
    final var auction = auctionService.getAuctionById(bidDto.getAuctionId());

    if (getMaxBidByAuctionId(bidDto.getAuctionId()) != null
            && bidDto.getAmount() <= getMaxBidByAuctionId(bidDto.getAuctionId()).getAmount()
        || bidDto.getAmount() <= auction.getStartPrice()) {
      throw new BidShouldBeMoreThenPreviousOne(
          "Bid amount should be more then previous one or start price");
    }

    final var bidder = userService.getUserById(UserPermissionService.getJwtUser().getId());

    final var bid =
        Bid.builder()
            .amount(bidDto.getAmount())
            .bidTime(LocalDateTime.now())
            .auction(auction)
            .bidder(bidder)
            .build();

    return bidRepository.save(bid);
  }

  @Override
  @Transactional(readOnly = true)
  public Bid getMaxBidByAuctionId(@Nonnull final Long auctionId) {
    if (!auctionValidator.isAuctionExistsInDbById(auctionId)) {
      throw new AuctionNotFoundException("Auction with id " + auctionId + " does not exist");
    }

    return bidRepository.findFirstByAuctionIdOrderByAmountDesc(auctionId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Bid> getBidsByAuctionId(@Nonnull final Long auctionId) {
    if (!auctionValidator.isAuctionExistsInDbById(auctionId)) {
      throw new AuctionNotFoundException("Auction with id " + auctionId + " does not exist");
    }

    return bidRepository.findAllByAuctionId(auctionId);
  }
}

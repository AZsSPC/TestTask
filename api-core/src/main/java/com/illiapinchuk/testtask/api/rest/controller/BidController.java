package com.illiapinchuk.testtask.api.rest.controller;

import com.illiapinchuk.testtask.common.mapper.BidMapper;
import com.illiapinchuk.testtask.model.dto.BidDto;
import com.illiapinchuk.testtask.service.BidService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for bids requests */
@RestController
@RequestMapping(value = "/bid")
@RequiredArgsConstructor
@Slf4j
public class BidController {

  private final BidService bidService;
  private final BidMapper bidMapper;

  /**
   * Creates a new bid.
   *
   * @param bidDto the bid to be created
   * @return a {@link ResponseEntity} containing the created bid and a suitable HTTP status code
   */
  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
  @PostMapping
  public ResponseEntity<BidDto> createBid(@Valid @RequestBody final BidDto bidDto) {
    final var bid = bidService.createBid(bidDto);
    final var bidResponse = bidMapper.bidToBidDto(bid);

    log.info("New bid was created with id: {}", bid.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(bidResponse);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
  @GetMapping("/auction/{auctionId}/max")
  public ResponseEntity<BidDto> getMaxBidByAuctionId(@Valid @PathVariable final Long auctionId) {
    final var bid = bidService.getMaxBidByAuctionId(auctionId);
    final var bidResponse = bidMapper.bidToBidDto(bid);

    log.info("Max bid for auction with id: {} was found", auctionId);
    return ResponseEntity.status(HttpStatus.FOUND).body(bidResponse);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
  @GetMapping("/auction/{auctionId}")
  public ResponseEntity<List<BidDto>> getBidsByAuctionId(
      @Valid @PathVariable final Long auctionId) {
    final var bids = bidService.getBidsByAuctionId(auctionId);
    final var bidResponse = bids.stream().map(bidMapper::bidToBidDto).collect(Collectors.toList());

    log.info("Bids for auction with id: {} were found", auctionId);
    return ResponseEntity.status(HttpStatus.FOUND).body(bidResponse);
  }
}

package com.illiapinchuk.testtask.api.rest.controller;

import com.illiapinchuk.testtask.common.mapper.AuctionMapper;
import com.illiapinchuk.testtask.model.dto.AuctionDto;
import com.illiapinchuk.testtask.service.AuctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for auction requests */
@RestController
@RequestMapping(value = "/auction")
@RequiredArgsConstructor
@Slf4j
public class AuctionController {

  private final AuctionService auctionService;
  private final AuctionMapper auctionMapper;

  @GetMapping("/{id}")
  public ResponseEntity<AuctionDto> getAuctionById(@PathVariable("id") final Long auctionId) {
    final var auction = auctionService.getAuctionById(auctionId);
    final var auctionResponse = auctionMapper.auctionToAuctionDto(auction);

    log.info("Auction with id: {} was found", auctionId);
    return ResponseEntity.ok(auctionResponse);
  }

  /**
   * Creates a new auction.
   *
   * @param auctionDto the auction to be created
   * @return a {@link ResponseEntity} containing the created auction and a suitable HTTP status code
   */
  @PostMapping
  public ResponseEntity<AuctionDto> createAuction(@Valid @RequestBody final AuctionDto auctionDto) {
    final var auctionRequest = auctionMapper.auctionDtoToAuction(auctionDto);
    final var auction = auctionService.createAuction(auctionRequest);
    final var auctionResponse = auctionMapper.auctionToAuctionDto(auction);

    log.info("New auction was created with id: {}", auction.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(auctionResponse);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER')")
  @PutMapping
  public ResponseEntity<AuctionDto> updateAuction(@Valid @RequestBody final AuctionDto auctionDto) {
    final var auction = auctionService.updateAuction(auctionDto);
    final var auctionResponse = auctionMapper.auctionToAuctionDto(auction);

    log.info("Auction with id: {} was updated", auction.getId());
    return ResponseEntity.ok().body(auctionResponse);
  }
}

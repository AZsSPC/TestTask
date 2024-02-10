package com.illiapinchuk.testtask.api.rest.controller;

import com.illiapinchuk.testtask.common.mapper.AuctionMapper;
import com.illiapinchuk.testtask.model.dto.AuctionDto;
import com.illiapinchuk.testtask.service.AuctionPhotosService;
import com.illiapinchuk.testtask.service.AuctionService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/** REST controller for auction requests */
@RestController
@RequestMapping(value = "/auction")
@RequiredArgsConstructor
@Slf4j
public class AuctionController {

  private final AuctionService auctionService;
  private final AuctionPhotosService auctionPhotosService;

  private final AuctionMapper auctionMapper;

  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
  @GetMapping
  public ResponseEntity<Page<AuctionDto>> getAllAuctions(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

    final var auctionPage = auctionService.getAllAuctions(page, size);
    return ResponseEntity.ok(auctionPage);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
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
   * @param auctionDtoJson the auction to be created
   * @return a {@link ResponseEntity} containing the created auction and a suitable HTTP status code
   */
  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
  @PostMapping
  public ResponseEntity<AuctionDto> createAuction(
      @Valid @RequestParam(value = "auction") final String auctionDtoJson,
      @RequestParam(value = "files", required = false) List<MultipartFile> files) {
    final var auctionDto = auctionMapper.fromJson(auctionDtoJson);
    final var auctionRequest = auctionMapper.auctionDtoToAuction(auctionDto);
    final var auction = auctionService.createAuction(auctionRequest);
    auctionPhotosService.addPhotosToAuction(auction.getId(), files);
    final var auctionResponse = auctionMapper.auctionToAuctionDto(auction);

    log.info("New auction was created with id: {}", auction.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(auctionResponse);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
  @PutMapping
  public ResponseEntity<AuctionDto> updateAuction(@Valid @RequestBody final AuctionDto auctionDto) {
    final var auction = auctionService.updateAuction(auctionDto);
    final var auctionResponse = auctionMapper.auctionToAuctionDto(auction);

    log.info("Auction with id: {} was updated", auction.getId());
    return ResponseEntity.ok().body(auctionResponse);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuctionById(@PathVariable("id") final Long auctionId) {
    auctionService.deleteAuctionById(auctionId);
    log.info("Auction with id: {} was deleted", auctionId);
    return ResponseEntity.ok().build();
  }
}

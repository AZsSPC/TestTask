package com.illiapinchuk.testtask.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/** Service for auction photos. */
public interface AuctionPhotosService {

  /**
   * Adds photos to the auction.
   *
   * @param auctionId the id of the auction to add photos to
   * @param photos the photos to add
   */
  void addPhotosToAuction(Long auctionId, List<MultipartFile> photos);
}

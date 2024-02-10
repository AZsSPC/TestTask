package com.illiapinchuk.testtask.service.impl;

import com.illiapinchuk.testtask.persistence.entity.AuctionPhoto;
import com.illiapinchuk.testtask.persistence.repository.AuctionPhotoRepository;
import com.illiapinchuk.testtask.service.AuctionPhotosService;
import com.illiapinchuk.testtask.service.AuctionService;
import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/** Implementation of {@link AuctionPhotosService} interface. */
@Service
@RequiredArgsConstructor
public class AuctionPhotosImpl implements AuctionPhotosService {

  private final AuctionService auctionService;
  private final S3FileUploadService s3FileUploadService;

  private final AuctionPhotoRepository auctionPhotoRepository;

  @Override
  public void addPhotosToAuction(
      @Nonnull final Long auctionId, @Nonnull final List<MultipartFile> photos) {
    final var auction = auctionService.getAuctionById(auctionId);

    // Process and save auction photos
    final var auctionPhotos =
        photos.stream()
            .map(
                file -> {
                  final String url = s3FileUploadService.uploadFile(file);
                  return AuctionPhoto.builder().auction(auction).url(url).build();
                })
            .collect(Collectors.toList());
    auctionPhotoRepository.saveAll(auctionPhotos);
  }
}

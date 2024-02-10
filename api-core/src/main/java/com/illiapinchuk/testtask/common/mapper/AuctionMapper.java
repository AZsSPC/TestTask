package com.illiapinchuk.testtask.common.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illiapinchuk.testtask.exception.CannotReadJsonException;
import com.illiapinchuk.testtask.model.dto.AuctionDto;
import com.illiapinchuk.testtask.persistence.entity.Auction;
import jakarta.annotation.Nonnull;
import java.io.IOException;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * This interface defines methods for mapping between the {@link Auction} and {@link AuctionDto}.
 */
@Mapper(componentModel = "spring")
public interface AuctionMapper {

  ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /**
   * Maps a {@link Auction} object to a {@link AuctionDto} object.
   *
   * @param auction The {@link Auction} object to be mapped.
   * @return The resulting {@link AuctionDto} object.
   */
  AuctionDto auctionToAuctionDto(Auction auction);

  /**
   * Maps a {@link AuctionDto} object to a {@link Auction} object.
   *
   * @param auctionDto The {@link AuctionDto} object to be mapped.
   * @return The resulting {@link Auction} object.
   */
  Auction auctionDtoToAuction(AuctionDto auctionDto);

  /**
   * This method updates the {@link Auction} object with the data from the {@link AuctionDto}.
   *
   * @param auction The Auction object to be updated.
   * @param auctionDto The source of the updated data.
   */
  void updateAuction(@MappingTarget Auction auction, AuctionDto auctionDto);

  default AuctionDto fromJson(@Nonnull final String json) {
    try {
      return OBJECT_MAPPER.readValue(json, AuctionDto.class);
    } catch (IOException ioException) {
      throw new CannotReadJsonException(
          String.format("Failed to read JSON data from the provided string: %s", json));
    }
  }
}

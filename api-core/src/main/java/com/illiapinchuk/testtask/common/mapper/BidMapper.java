package com.illiapinchuk.testtask.common.mapper;

import com.illiapinchuk.testtask.model.dto.BidDto;
import com.illiapinchuk.testtask.persistence.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** This interface defines methods for mapping between the {@link Bid} and {@link BidDto}. */
@Mapper(componentModel = "spring")
public interface BidMapper {

  /**
   * Maps a {@link Bid} object to a {@link BidDto} object.
   *
   * @param bid The {@link Bid} object to be mapped.
   * @return The resulting {@link BidDto} object.
   */
  @Mapping(source = "auction.id", target = "auctionId")
  @Mapping(source = "bidder.id", target = "bidderId")
  BidDto bidToBidDto(Bid bid);

  /**
   * Maps a {@link BidDto} object to a {@link Bid} object.
   *
   * @param bidDto The {@link BidDto} object to be mapped.
   * @return The resulting {@link Bid} object.
   */
  @Mapping(source = "auctionId", target = "auction.id")
  @Mapping(source = "bidderId", target = "bidder.id")
  Bid bidDtoToBid(BidDto bidDto);
}

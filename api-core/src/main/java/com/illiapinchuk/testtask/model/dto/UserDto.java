package com.illiapinchuk.testtask.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illiapinchuk.testtask.model.entity.RoleName;
import com.illiapinchuk.testtask.persistence.entity.Auction;
import com.illiapinchuk.testtask.persistence.entity.Bid;
import com.illiapinchuk.testtask.persistence.entity.User;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/** Incoming DTO to represent {@link User}. */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  Long id;

  String firstname;
  String lastname;
  String login;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  String password;

  List<Auction> createdAuctions;
  List<Bid> bids;
  Set<RoleName> roles;
}

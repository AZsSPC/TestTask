package com.illiapinchuk.testtask.common.mapper;

import com.illiapinchuk.testtask.model.dto.UserDto;
import com.illiapinchuk.testtask.persistence.entity.User;
import org.mapstruct.Mapper;

/** This interface defines methods for mapping between the {@link User}, {@link UserDto}. */
@Mapper(componentModel = "spring")
public interface UserMapper {

  /**
   * Maps a {@link User} object to a {@link UserDto} object.
   *
   * @param user The {@link User} object to be mapped.
   * @return The resulting {@link UserDto} object.
   */
  UserDto userToUserDto(User user);

  /**
   * Maps a {@link UserDto} object to a {@link User} object.
   *
   * @param userDto The {@link UserDto} object to be mapped.
   * @return The resulting {@link User} object.
   */
  User userDtoToUser(UserDto userDto);
}

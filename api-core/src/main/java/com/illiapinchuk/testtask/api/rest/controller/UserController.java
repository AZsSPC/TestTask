package com.illiapinchuk.testtask.api.rest.controller;

import com.illiapinchuk.testtask.common.mapper.UserMapper;
import com.illiapinchuk.testtask.model.dto.UserDto;
import com.illiapinchuk.testtask.persistence.entity.User;
import com.illiapinchuk.testtask.service.UserService;
import jakarta.validation.Valid;
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

/** REST controller for user. */
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'USER')")
  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") final Long id) {

    final var user = userService.getUserById(id);
    final var userResponse = userMapper.userToUserDto(user);

    log.info("User with id: {} was found", id);
    return ResponseEntity.ok(userResponse);
  }

  /**
   * Registers a new user by creating a new {@link User} with the information provided in the
   * request body.
   *
   * @param userDto a {@link UserDto} object containing the user's information
   * @return a {@link ResponseEntity} object with a status of 201 (Created) and the created {@link
   *     UserDto} object in the body.
   */
  @PostMapping
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody final UserDto userDto) {
    final var userRequest = userMapper.userDtoToUser(userDto);
    final var user = userService.createUser(userRequest);
    final var userResponse = userMapper.userToUserDto(user);

    log.info("New user was created with id: {}", user.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
  }
}

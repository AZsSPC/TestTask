package com.illiapinchuk.testtask.service;

import com.illiapinchuk.testtask.persistence.entity.User;

/** Service interface for managing users. */
public interface UserService {

  /**
   * Retrieves a user by their unique identifier.
   *
   * @param id The unique identifier of the user.
   * @return The {@link User} object if found, otherwise null.
   */
  User getUserById(Long id);

  /**
   * Retrieves a user by their login.
   *
   * @param login The login of the user.
   * @return The {@link User} object if found, otherwise null.
   */
  User getUserByLogin(String login);

  /**
   * Creates a new user.
   *
   * @param user The {@link User} object representing the user to be created.
   * @return The created {@link User} object.
   */
  User createUser(User user);
}

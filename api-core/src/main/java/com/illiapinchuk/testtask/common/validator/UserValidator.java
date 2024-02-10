package com.illiapinchuk.testtask.common.validator;

import com.illiapinchuk.testtask.persistence.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** Validation for user-related information. */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserValidator {

  private final UserRepository userRepository;

  /**
   * Check if the given login exists in the database.
   *
   * @param login the login to check
   * @return true if the login exists in the database, false otherwise
   */
  public boolean isUserExistsByLogin(@Nonnull final String login) {
    return userRepository.existsByLogin(login);
  }

  /**
   * Check if the given user with id exists in the database.
   *
   * @param id the id to check
   * @return true if the user exists in the database, false otherwise
   */
  public boolean isUserExistInDbById(@Nonnull final Long id) {
    return userRepository.existsById(id);
  }
}

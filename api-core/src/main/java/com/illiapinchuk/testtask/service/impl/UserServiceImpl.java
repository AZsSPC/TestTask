package com.illiapinchuk.testtask.service.impl;

import com.illiapinchuk.testtask.common.validator.UserValidator;
import com.illiapinchuk.testtask.exception.UserNotFoundException;
import com.illiapinchuk.testtask.model.entity.RoleName;
import com.illiapinchuk.testtask.persistence.entity.User;
import com.illiapinchuk.testtask.persistence.repository.UserRepository;
import com.illiapinchuk.testtask.service.UserService;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityExistsException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserValidator userValidator;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User getUserById(@Nonnull final Long id) {
    return userRepository.getReferenceById(id);
  }

  @Override
  public User getUserByLogin(@Nonnull final String login) {
    return userRepository
        .findUserByLogin(login)
        .orElseThrow(
            () -> new UserNotFoundException(String.format("User with login %s not found", login)));
  }

  @Override
  public User createUser(@Nonnull final User user) {
    if (userValidator.isUserExistsByLogin(user.getLogin())) {
      throw new EntityExistsException("User with current login already exists in the database.");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    // By default user role is USER
    user.setRoles(Set.of(RoleName.USER));
    return userRepository.save(user);
  }
}

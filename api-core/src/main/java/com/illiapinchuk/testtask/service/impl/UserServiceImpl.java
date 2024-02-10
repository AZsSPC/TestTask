package com.illiapinchuk.testtask.service.impl;

import com.illiapinchuk.testtask.exception.UserNotFoundException;
import com.illiapinchuk.testtask.persistence.entity.User;
import com.illiapinchuk.testtask.persistence.repository.UserRepository;
import com.illiapinchuk.testtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getUserByLogin(String login) {
    return userRepository
        .findUserByLogin(login)
        .orElseThrow(
            () -> new UserNotFoundException(String.format("User with login %s not found", login)));
  }
}

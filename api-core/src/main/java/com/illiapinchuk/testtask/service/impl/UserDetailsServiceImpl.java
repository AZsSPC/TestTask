package com.illiapinchuk.testtask.service.impl;

import com.illiapinchuk.testtask.configuration.security.jwt.JwtUser;
import com.illiapinchuk.testtask.persistence.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** Implementation of {@link UserDetailsService} interface. */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(@Nonnull final String username)
      throws UsernameNotFoundException {
    return JwtUser.build(
        userRepository
            .findUserByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("User with current login not found")));
  }
}

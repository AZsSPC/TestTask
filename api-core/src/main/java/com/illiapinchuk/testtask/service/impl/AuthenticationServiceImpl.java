package com.illiapinchuk.testtask.service.impl;

import com.illiapinchuk.testtask.configuration.security.jwt.JwtTokenProviderImpl;
import com.illiapinchuk.testtask.model.dto.AuthRequestDto;
import com.illiapinchuk.testtask.service.AuthenticationService;
import com.illiapinchuk.testtask.service.UserService;
import jakarta.annotation.Nonnull;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/** Implementation of {@link AuthenticationService} interface. */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProviderImpl jwtTokenProviderImpl;
  private final UserService userService;

  @Override
  public Map<Object, Object> login(@Nonnull final AuthRequestDto requestDto) {
    final var login = requestDto.getLogin();
    final var password = requestDto.getPassword();

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

      final var user = userService.getUserByLogin(login);

      final var token = jwtTokenProviderImpl.createToken(login, user.getRoles());

      return Map.of("login", login, "token", token);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }
}

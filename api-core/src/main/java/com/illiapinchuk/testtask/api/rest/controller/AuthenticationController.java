package com.illiapinchuk.testtask.api.rest.controller;

import com.illiapinchuk.testtask.model.dto.AuthRequestDto;
import com.illiapinchuk.testtask.service.AuthenticationService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for authentication requests (login, logout, register, etc.) */
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Login user.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @PostMapping("/login")
  public ResponseEntity<Map<Object, Object>> login(
      @Valid @Nonnull @RequestBody final AuthRequestDto requestDto) {
    return ResponseEntity.ok(authenticationService.login(requestDto));
  }
}

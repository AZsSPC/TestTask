package com.illiapinchuk.testtask.service;

import com.illiapinchuk.testtask.model.dto.AuthRequestDto;
import java.util.Map;

/** The AuthenticationService interface defines methods for user authentication. */
public interface AuthenticationService {

  Map<Object, Object> login(AuthRequestDto requestDto);
}

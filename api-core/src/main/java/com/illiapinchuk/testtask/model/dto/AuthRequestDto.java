package com.illiapinchuk.testtask.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** DTO class for authentication (login) request. */
@EqualsAndHashCode
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {

  @NotBlank
  private String login;

  @NotBlank
  private String password;
}

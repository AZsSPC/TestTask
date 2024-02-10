package com.illiapinchuk.testtask.configuration.security;

import com.illiapinchuk.testtask.common.ApplicationConstants;
import com.illiapinchuk.testtask.configuration.handler.FilterChainExceptionHandler;
import com.illiapinchuk.testtask.configuration.security.jwt.JwtConfigurer;
import com.illiapinchuk.testtask.configuration.security.jwt.JwtTokenProvider;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/** Security configuration class for JWT based Spring Security application. */
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtTokenProvider jwtTokenProvider;
  private final FilterChainExceptionHandler filterChainExceptionHandler;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      @Nonnull final AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  /** Configuration of security. */
  @Bean
  public SecurityFilterChain filterChain(@Nonnull final HttpSecurity http) throws Exception {

    http
        // disable CSRF as we do not serve browser clients
        .csrf()
        .disable()
        .authorizeHttpRequests()
        // authenticate requests
        .requestMatchers(
            new AntPathRequestMatcher(ApplicationConstants.Web.Path.LOGIN_PATH),
            new AntPathRequestMatcher(
                ApplicationConstants.Web.Path.SIGN_UP_PATH, HttpMethod.POST.name()))
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
        // JWT authorization filter
        .apply(new JwtConfigurer(jwtTokenProvider))
        .and()
        // make sure we use stateless session, session will not be used to store user's state
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
  }
}

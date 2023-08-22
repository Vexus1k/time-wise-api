package com.example.timewiseapi.config;

import com.example.timewiseapi.auth.exception.InvalidTokenException;
import com.example.timewiseapi.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

  private final TokenRepository tokenRepository;

  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String jwt = authHeader.substring(7);

      if (isTokenValid(jwt)) {
        invalidateToken(jwt);
        clearAuthenticationContext();
        return;
      }
    }

    throw new InvalidTokenException("Invalid token");
  }

  private boolean isTokenValid(String jwt) {
    return tokenRepository.findByToken(jwt)
      .map(storedToken -> !storedToken.isExpired() && !storedToken.isRevoked())
      .orElse(false);
  }

  private void invalidateToken(String jwt) {
    tokenRepository.findByToken(jwt)
      .ifPresent(storedToken -> {
        storedToken.setExpired(true);
        storedToken.setRevoked(true);
        storedToken.setExpiredDate(LocalDateTime.now());
        tokenRepository.save(storedToken);
      });
  }

  private void clearAuthenticationContext() {
    SecurityContextHolder.clearContext();
  }
}

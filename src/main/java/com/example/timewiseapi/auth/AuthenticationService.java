package com.example.timewiseapi.auth;

import com.example.timewiseapi.auth.exception.UserAlreadyExistsException;
import com.example.timewiseapi.auth.projection.RefreshTokenResponse;
import com.example.timewiseapi.config.JwtService;
import com.example.timewiseapi.token.Token;
import com.example.timewiseapi.token.TokenRepository;
import com.example.timewiseapi.token.TokenType;
import com.example.timewiseapi.user.*;
import com.example.timewiseapi.user.projection.UserEditableFields;
import com.example.timewiseapi.user.projection.UserResponse;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final EmailService emailService;

  public UserResponse updateUser(UserEditableFields updateRequest, String accessToken) {

    User user = repository.findById(updateRequest.getId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

    if (!user.getActivated()) {
      throw new IllegalStateException("User is not activated.");
    }

    if (!isAccessTokenValid(user, accessToken)) {
      throw new AccessDeniedException("Access token is not valid for this user.");
    }

    if (updateRequest.getFirstName() != null && !updateRequest.getFirstName().equals(user.getFirstName())) {
      user.setFirstName(updateRequest.getFirstName());
    }
    if (updateRequest.getLastName() != null && !updateRequest.getLastName().equals(user.getLastName())) {
      user.setLastName(updateRequest.getLastName());
    }
    if (updateRequest.getEmail() != null && !updateRequest.getEmail().equals(user.getEmail())) {
      user.setEmail(updateRequest.getEmail());
    }
    if (updateRequest.getPhoneNumber() != null && !updateRequest.getPhoneNumber().equals(user.getPhoneNumber())) {
      user.setPhoneNumber(updateRequest.getPhoneNumber());
    }
    if (updateRequest.getPassword() != null && StringUtils.isNotBlank(updateRequest.getPassword())) {
      user.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
    }

    repository.save(user);

    return mapToUserResponse(user);
  }

  private boolean isAccessTokenValid(User user, String accessToken) {
    List<Token> validTokens = tokenRepository.findAllValidTokenByUser(user.getId());

    return validTokens.stream()
      .anyMatch(token -> token.getToken().equals(accessToken));
  }

  public AuthenticationResponse register(RegisterRequest request) {
    if (repository.existsByEmail(request.getEmail())) {
      throw new UserAlreadyExistsException("User with this email already exists.");
    }

    var user = User.builder()
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .registrationDate(LocalDateTime.now())
      .role(request.getRole())
      .activated(false)
      .activationToken(generateActivationToken())
      .build();

    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    saveUserToken(savedUser, jwtToken);
    sendActivationEmail(savedUser.getEmail(), user.getActivationToken());

    return AuthenticationResponse.builder()
      .user(mapToUserResponse(user))
      .accessToken(jwtToken)
      .refreshToken(refreshToken)
      .build();
  }

  public void activateAccount(String activationToken) {
    User user = repository.findByActivationToken(activationToken)
      .orElseThrow(() -> new EntityNotFoundException("Invalid activation token."));

    user.setActivated(true);
    repository.save(user);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      )
    );

    var user = repository.findByEmail(request.getEmail())
      .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);

    return AuthenticationResponse.builder()
      .user(mapToUserResponse(user))
      .accessToken(jwtToken)
      .refreshToken(refreshToken)
      .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
      .user(user)
      .token(jwtToken)
      .tokenType(TokenType.BEARER)
      .expired(false)
      .revoked(false)
      .build();
    tokenRepository.save(token);
  }

  public RefreshTokenResponse refreshToken(String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return new RefreshTokenResponse(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header.");
    }

    final String refreshToken = authHeader.substring(7);
    final String userEmail = jwtService.extractUsername(refreshToken);

    if (userEmail != null) {
      try {
        var user = this.repository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User with the provided email does not exist."));

        if (jwtService.isTokenValid(refreshToken, user)) {
          var accessToken = jwtService.generateToken(user);
          revokeAllUserTokens(user);
          saveUserToken(user, accessToken);

          var authResponse = AuthenticationResponse.builder()
                  .accessToken(accessToken)
                  .refreshToken(refreshToken)
                  .user(mapToUserResponse(user))
                  .build();

          return new RefreshTokenResponse(HttpStatus.OK, authResponse);
        }
      } catch (EntityNotFoundException ex) {
        return new RefreshTokenResponse(HttpStatus.NOT_FOUND, ex.getMessage());
      } catch (Exception e) {
        return new RefreshTokenResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.");
      }
    }

    return new RefreshTokenResponse(HttpStatus.UNAUTHORIZED, "Invalid or expired refresh token.");
  }

  private String generateActivationToken() {
    return UUID.randomUUID().toString();
  }

  private void sendActivationEmail(String email, String activationToken) {
    String activationLink = "http://localhost:8080/api/v1/auth/activate?token=" + activationToken;

    String emailContent = "Please click the following link to activate your account: " + activationLink;

    emailService.sendEmail(email, "Account Activation", emailContent);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpiredDate(LocalDateTime.now());
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  private static UserResponse mapToUserResponse(User user) {
    return UserResponse.builder()
      .id(user.getId())
      .firstName(user.getFirstName())
      .lastName(user.getLastName())
      .email(user.getEmail())
      .phoneNumber(user.getPhoneNumber())
      .role(user.getRole())
      .activated(user.getActivated())
      .registrationDate(user.getRegistrationDate())
      .build();
  }
}
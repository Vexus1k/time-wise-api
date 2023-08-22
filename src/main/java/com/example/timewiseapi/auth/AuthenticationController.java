package com.example.timewiseapi.auth;

import com.example.timewiseapi.auth.projection.RefreshTokenResponse;
import com.example.timewiseapi.config.LogoutService;
import com.example.timewiseapi.user.projection.UserEditableFields;
import com.example.timewiseapi.user.projection.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

  private final AuthenticationService service;
  private final LogoutService logoutService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @Valid @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @GetMapping("/activate")
  public ResponseEntity<String> activateAccount(@RequestParam("token") String activationToken) {
    try {
      service.activateAccount(activationToken);
      return ResponseEntity.ok("Account activated successfully.");
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid activation token.");
    }
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<?> refreshToken(HttpServletRequest request) {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    RefreshTokenResponse response = service.refreshToken(authHeader);

    return ResponseEntity.status(response.status()).body(response.response());
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<UserResponse> updateUserById(
      @PathVariable Integer id,
      @RequestBody UserEditableFields updateRequest,
      @RequestHeader("Authorization") String authorizationHeader
  ) {
    String token = authorizationHeader.substring(7);
    updateRequest.setId(id);
    UserResponse updatedUser = service.updateUser(updateRequest, token);

    return ResponseEntity.ok(updatedUser);
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    logoutService.logout(request, response, authentication);

    return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Logged out successfully\"}");
  }
}

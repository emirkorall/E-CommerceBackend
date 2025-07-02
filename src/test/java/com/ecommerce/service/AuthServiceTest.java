package com.ecommerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ecommerce.auth.AuthConverter;
import com.ecommerce.auth.AuthService;
import com.ecommerce.auth.dto.AuthResponse;
import com.ecommerce.config.JwtService;
import com.ecommerce.role.RoleRepository;
import com.ecommerce.user.User;
import com.ecommerce.user.UserRepository;
import com.ecommerce.user.dto.LoginRequest;
import com.ecommerce.user.dto.SignupRequest;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthServiceTest {
  @Mock AuthenticationManager authenticationManager;
  @Mock UserRepository userRepository;
  @Mock PasswordEncoder passwordEncoder;
  @Mock RoleRepository roleRepository;
  @Mock AuthConverter authConverter;
  @Mock JwtService jwtService;
  @InjectMocks AuthService authService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void signup_success() {
    // Test signup success
    SignupRequest signupRequest =
        new SignupRequest("Test", "User", "test@example.com", "password", "USER");
    User user = new User();
    user.setAuthority("USER");
    when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
    when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
    when(userRepository.save(any(User.class))).thenReturn(user);
    when(jwtService.generateToken(any(User.class))).thenReturn("jwt-token");
    when(authConverter.toResponse(any(User.class), anyString(), anyString()))
        .thenReturn(
            new AuthResponse(
                1L, "Test", "test@example.com", "USER", "Registration succesfull", "jwt-token"));
    AuthResponse response = authService.signup(signupRequest);
    assertNotNull(response);
    assertEquals("jwt-token", response.token());
  }

  @Test
  void login_success() {
    // Test login success
    LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
    User user = new User();
    user.setAuthority("USER");
    Authentication authentication = mock(Authentication.class);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(authentication);
    when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
    when(jwtService.generateToken(user)).thenReturn("jwt-token");
    when(authConverter.toResponse(any(User.class), anyString(), anyString()))
        .thenReturn(
            new AuthResponse(
                1L, "Test", "test@example.com", "USER", "Login succesfull", "jwt-token"));
    AuthResponse response = authService.login(loginRequest);
    assertNotNull(response);
    assertEquals("jwt-token", response.token());
  }
}

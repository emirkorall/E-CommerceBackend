package com.ecommerce.auth;

import com.ecommerce.auth.dto.AuthResponse;
import com.ecommerce.config.JwtService;
import com.ecommerce.exception.ApiException;
import com.ecommerce.user.User;
import com.ecommerce.user.UserRepository;
import com.ecommerce.user.dto.LoginRequest;
import com.ecommerce.user.dto.SignupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthConverter authConverter;
  private final JwtService jwtService;

  public AuthService(
      AuthenticationManager authenticationManager,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      AuthConverter authConverter,
      JwtService jwtService) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authConverter = authConverter;
    this.jwtService = jwtService;
  }

  public AuthResponse signup(SignupRequest request) {
    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new ApiException("Email already exists", HttpStatus.CONFLICT);
    }
    User user = new User();
    user.setName(request.name());
    user.setSurname(request.surname());
    user.setEmail(request.email());
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setAuthority(request.authority().toUpperCase());
    User savedUser = userRepository.save(user);
    String token = jwtService.generateToken(savedUser);
    return authConverter.toResponse(savedUser, "Registration succesful", token);
  }

  public AuthResponse login(LoginRequest request) {
    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.email(), request.password()));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      User user =
          userRepository
              .findByEmail(request.email())
              .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

      String token = jwtService.generateToken(user);

      return authConverter.toResponse(user, "Login succesful", token);

    } catch (AuthenticationException e) {
      throw new ApiException("Invalid email or password", HttpStatus.UNAUTHORIZED);
    }
  }
}

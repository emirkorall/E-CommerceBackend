package com.ecommerce.service;

import com.ecommerce.config.JwtService;
import com.ecommerce.converter.AuthConverter;
import com.ecommerce.dto.request.SignupRequest;
import com.ecommerce.dto.request.LoginRequest;
import com.ecommerce.dto.response.AuthResponse;
import com.ecommerce.entity.Role;
import com.ecommerce.entity.User;
import com.ecommerce.exception.ApiException;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
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
    private final RoleRepository roleRepository;
    private final AuthConverter authConverter;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthConverter authConverter, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authConverter = authConverter;
        this.jwtService = jwtService;
    }

    public AuthResponse signup(SignupRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new ApiException("Email already exists", HttpStatus.CONFLICT);
        }

        Role userRole = roleRepository.findByAuthority("ROLE_USER")
                .orElseThrow(() -> new ApiException("User Role not set", HttpStatus.INTERNAL_SERVER_ERROR));

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(userRole);

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        return authConverter.toResponse(savedUser, "Registration succesfull", token);
    }

    public AuthResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

            String token = jwtService.generateToken(user);

            return authConverter.toResponse(user, "Login succesfull", token);

        } catch (AuthenticationException e) {
            throw new ApiException("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}

package com.ecommerce.user;

import com.ecommerce.exception.ApiException;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserConverter userconverter;

  public UserServiceImp(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      UserConverter userconverter) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userconverter = userconverter;
  }

  @Override
  public List<UserResponse> findAllUsers() {
    return userRepository.findAll().stream()
        .map(userconverter::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public UserResponse findUserById(long id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
    return userconverter.toResponse(user);
  }

  @Override
  public UserResponse saveUser(UserRequest request) {
    if (request == null) {
      throw new ApiException("User data must not be null", HttpStatus.BAD_REQUEST);
    }
    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new ApiException("Email already exists", HttpStatus.CONFLICT);
    }

    String authority = "USER";
    User user = userconverter.toEntity(request, authority, passwordEncoder);
    User savedUser = userRepository.save(user);
    return userconverter.toResponse(savedUser);
  }

  @Override
  public UserResponse updateUser(long id, UserRequest request) {
    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
    userconverter.updateEntity(existingUser, request, passwordEncoder);
    return userconverter.toResponse(userRepository.save(existingUser));
  }

  @Override
  public UserResponse deleteUserById(long id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
    userRepository.delete(user);
    return userconverter.toResponse(user);
  }
}

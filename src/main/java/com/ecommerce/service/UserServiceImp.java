package com.ecommerce.service;

import com.ecommerce.converter.UserConverter;
import com.ecommerce.dto.request.UserRequest;
import com.ecommerce.dto.response.UserResponse;
import com.ecommerce.entity.Role;
import com.ecommerce.entity.User;
import com.ecommerce.exception.ApiException;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service


public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserConverter userconverter;


    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserConverter userconverter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        return userconverter.toResponse(user);

    }

    @Override
    public UserResponse saveUser(UserRequest request) {
        if (request == null) {
            throw new ApiException("User data must not be null", HttpStatus.BAD_REQUEST);
        }
        //Check if the user's email is already registered before//
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new ApiException("Email already exists", HttpStatus.CONFLICT);
        }
        Role userRole = roleRepository.findByAuthority("ROLE_USER")
                .orElseThrow(() -> new ApiException("User Role not set", HttpStatus.INTERNAL_SERVER_ERROR));
        User user = userconverter.toEntity(request, userRole, passwordEncoder);
        User savedUser = userRepository.save(user);
        return userconverter.toResponse(savedUser);
    }

    @Override

    public UserResponse updateUser(long id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        User updated = userconverter.toEntity(request, existingUser.getRole(), passwordEncoder);
        updated.setId(id);
        return userconverter.toResponse(userRepository.save(updated));
    }


    @Override
    public UserResponse deleteUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
        return userconverter.toResponse(user);
    }
}

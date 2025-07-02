package com.ecommerce.role;

import com.ecommerce.exception.ApiException;
import com.ecommerce.role.dto.RoleRequest;
import com.ecommerce.role.dto.RoleResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleConverter roleConverter;

  public RoleServiceImp(RoleRepository roleRepository, RoleConverter roleConverter) {
    this.roleRepository = roleRepository;
    this.roleConverter = roleConverter;
  }

  @Override
  public List<RoleResponse> findAllRoles() {
    return roleRepository.findAll().stream()
        .map(roleConverter::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public RoleResponse findRoleById(long id) {
    Role role =
        roleRepository
            .findById(id)
            .orElseThrow(
                () -> new ApiException("Role not found with id: " + id, HttpStatus.NOT_FOUND));
    return roleConverter.toResponse(role);
  }

  @Override
  public RoleResponse saveRole(RoleRequest request) {
    if (request == null) {
      throw new ApiException("Role data must not be null", HttpStatus.BAD_REQUEST);
    }
    Role role = roleConverter.toEntity(request);
    Role savedRole = roleRepository.save(role);
    return roleConverter.toResponse(roleRepository.save(savedRole));
  }

  @Override
  public RoleResponse updateRole(long id, RoleRequest request) {
    Role existingRole =
        roleRepository
            .findById(id)
            .orElseThrow(
                () -> new ApiException("Role not found with id: " + id, HttpStatus.NOT_FOUND));
    roleConverter.updateEntity(existingRole, request);
    return roleConverter.toResponse(roleRepository.save(existingRole));
  }

  @Override
  public RoleResponse deleteRoleById(long id) {
    Role role =
        roleRepository
            .findById(id)
            .orElseThrow(
                () -> new ApiException("Role not found with id: " + id, HttpStatus.NOT_FOUND));
    roleRepository.delete(role);
    return roleConverter.toResponse(role);
  }
}

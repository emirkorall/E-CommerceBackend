package com.ecommerce.role;

import com.ecommerce.role.dto.RoleRequest;
import com.ecommerce.role.dto.RoleResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@Validated
public class RoleController {

  private final RoleService roleService;

  public RoleController(RoleService roleService, RoleConverter roleConverter) {
    this.roleService = roleService;
  }

  @GetMapping
  public ResponseEntity<List<RoleResponse>> getAllRoles() {
    return ResponseEntity.ok(roleService.findAllRoles());
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoleResponse> getRoleById(@PathVariable long id) {
    return ResponseEntity.ok(roleService.findRoleById(id));
  }

  @PostMapping
  public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody RoleRequest request) {
    RoleResponse response = roleService.saveRole(request);
    return ResponseEntity.status(201).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RoleResponse> updateRole(
      @PathVariable long id, @Valid @RequestBody RoleRequest request) {
    return ResponseEntity.ok(roleService.updateRole(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RoleResponse> deleteRole(@PathVariable long id) {
    return ResponseEntity.ok(roleService.deleteRoleById(id));
  }
}

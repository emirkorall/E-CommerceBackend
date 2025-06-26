package com.ecommerce.role;

import com.ecommerce.role.dto.RoleRequest;
import com.ecommerce.role.dto.RoleResponse;
import java.util.List;

public interface RoleService {

  List<RoleResponse> findAllRoles();

  RoleResponse findRoleById(long id);

  RoleResponse saveRole(RoleRequest request);

  RoleResponse updateRole(long id, RoleRequest request);

  RoleResponse deleteRoleById(long id);
}

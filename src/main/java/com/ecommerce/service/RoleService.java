package com.ecommerce.service;

import com.ecommerce.dto.request.RoleRequest;
import com.ecommerce.dto.response.RoleResponse;
import com.ecommerce.entity.Role;

import java.util.List;

public interface RoleService {

    List<RoleResponse> findAllRoles();

    RoleResponse findRoleById(long id);

    RoleResponse saveRole(RoleRequest request);

    RoleResponse updateRole(long id, RoleRequest request);

    RoleResponse deleteRoleById(long id);
}

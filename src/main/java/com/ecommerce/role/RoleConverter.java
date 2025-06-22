package com.ecommerce.role;


import com.ecommerce.role.dto.RoleRequest;
import com.ecommerce.role.dto.RoleResponse;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public Role toEntity(RoleRequest request) {
        Role role = new Role();
        role.setAuthority(request.authority());
        return role;
    }


    public RoleResponse toResponse(Role entity) {
        return new RoleResponse(entity.getId(),
                entity.getAuthority());
    }
}

package com.ecommerce.converter;


import com.ecommerce.dto.request.RoleRequest;
import com.ecommerce.dto.response.RoleResponse;
import com.ecommerce.entity.Role;
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

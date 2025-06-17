package com.ecommerce.converter;


import com.ecommerce.dto.request.AddressRequest;
import com.ecommerce.dto.response.AddressResponse;
import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address toEntity(AddressRequest dto, User user) {
        Address address = new Address();
        address.setTitle(dto.title());
        address.setName(dto.name());
        address.setSurname(dto.surname());
        address.setPhone(dto.phone());
        address.setCity(dto.city());
        address.setDistrict(dto.district());
        address.setNeighborhood(dto.neighborhood());
        address.setUser(user);
        return address;
    }

    public AddressResponse toResponse(Address entity) {
        return new AddressResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getName(),
                entity.getSurname(),
                entity.getPhone(),
                entity.getCity(),
                entity.getDistrict(),
                entity.getNeighborhood());
    }

}




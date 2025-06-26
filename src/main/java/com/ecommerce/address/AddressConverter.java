package com.ecommerce.address;

import com.ecommerce.address.dto.AddressRequest;
import com.ecommerce.address.dto.AddressResponse;
import com.ecommerce.user.User;
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

  public void updateEntity(Address address, AddressRequest dto, User user) {
    address.setTitle(dto.title());
    address.setName(dto.name());
    address.setSurname(dto.surname());
    address.setPhone(dto.phone());
    address.setCity(dto.city());
    address.setDistrict(dto.district());
    address.setNeighborhood(dto.neighborhood());
    address.setUser(user);
  }
}

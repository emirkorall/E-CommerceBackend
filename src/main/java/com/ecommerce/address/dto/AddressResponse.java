package com.ecommerce.address.dto;

public record AddressResponse(Long id,
                              String title,
                              String name,
                              String surname,
                              String phone,
                              String city,
                              String district,
                              String neighborhood) {
}

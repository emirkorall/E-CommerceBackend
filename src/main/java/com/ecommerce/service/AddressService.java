package com.ecommerce.service;

import com.ecommerce.dto.request.AddressRequest;
import com.ecommerce.dto.response.AddressResponse;
import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;

import java.util.List;

public interface AddressService {

    List<AddressResponse> findAllAddresses();

    AddressResponse findAddressById(long id);

    AddressResponse saveAddress(AddressRequest request, User user);

    AddressResponse updateAddress(long id, AddressRequest request, User user);

    AddressResponse deleteAddressById(long id);
}

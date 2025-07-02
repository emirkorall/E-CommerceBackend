package com.ecommerce.address;

import com.ecommerce.address.dto.AddressRequest;
import com.ecommerce.address.dto.AddressResponse;
import com.ecommerce.user.User;
import java.util.List;

public interface AddressService {

  List<AddressResponse> findAllAddresses(User user);

  AddressResponse findAddressById(long id, User user);

  AddressResponse saveAddress(AddressRequest request, User user);

  AddressResponse updateAddress(long id, AddressRequest request, User user);

  AddressResponse deleteAddressById(long id, User user);
}

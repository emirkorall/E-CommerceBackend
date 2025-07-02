package com.ecommerce.address;

import com.ecommerce.address.dto.AddressRequest;
import com.ecommerce.address.dto.AddressResponse;
import com.ecommerce.exception.ApiException;
import com.ecommerce.user.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {

  private final AddressRepository addressRepository;
  private final AddressConverter addressConverter;

  public AddressServiceImp(AddressRepository addressRepository, AddressConverter addressConverter) {
    this.addressRepository = addressRepository;
    this.addressConverter = addressConverter;
  }

  @Override
  public List<AddressResponse> findAllAddresses(User user) {
    return addressRepository.findAllByUser(user).stream()
        .map(addressConverter::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public AddressResponse findAddressById(long id, User user) {
    Address address =
        addressRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Address not found", HttpStatus.NOT_FOUND));
    return addressConverter.toResponse(address);
  }

  @Override
  public AddressResponse saveAddress(AddressRequest request, User user) {
    if (request == null) {
      throw new ApiException("Address data must not be null", HttpStatus.BAD_REQUEST);
    }
    Address address = addressConverter.toEntity(request, user);
    Address savedAddress = addressRepository.save(address);
    return addressConverter.toResponse(savedAddress);
  }

  @Override
  public AddressResponse updateAddress(long id, AddressRequest request, User user) {
    Address existingAddress =
        addressRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Address not found", HttpStatus.NOT_FOUND));
    addressConverter.updateEntity(existingAddress, request, user);
    return addressConverter.toResponse(addressRepository.save(existingAddress));
  }

  @Override
  public AddressResponse deleteAddressById(long id, User user) {
    Address address =
        addressRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Address not found", HttpStatus.NOT_FOUND));
    addressRepository.delete(address);
    return addressConverter.toResponse(address);
  }
}

package com.ecommerce.address;


import com.ecommerce.address.dto.AddressRequest;
import com.ecommerce.address.dto.AddressResponse;
import com.ecommerce.user.User;
import com.ecommerce.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    public AddressServiceImp(AddressRepository addressRepository, AddressConverter addressConverter) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }


    @Override
    public List<AddressResponse> findAllAddresses() {
        return addressRepository.findAll().stream()
                .map(addressConverter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse findAddressById(long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ApiException("Address not found with id: " + id, HttpStatus.NOT_FOUND));
        return addressConverter.toResponse(address);

    }


    @Override
    public AddressResponse saveAddress(AddressRequest request, User user) {
        if (request == null) {
            throw new ApiException("Address data must not be null", HttpStatus.BAD_REQUEST);
        }
        Address address = addressConverter.toEntity(request, user);
        return addressConverter.toResponse(addressRepository.save(address));
    }


    @Override
    public AddressResponse updateAddress(long id, AddressRequest request, User user) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ApiException("Address not found with id: " + id, HttpStatus.NOT_FOUND));
        Address updated = addressConverter.toEntity(request, user);
        updated.setId(id);
        return addressConverter.toResponse(addressRepository.save(updated));

    }

    @Override
    public AddressResponse deleteAddressById(long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ApiException("Address not found with id: " + id, HttpStatus.NOT_FOUND));
        addressRepository.delete(address);
        return addressConverter.toResponse(address);
    }

}

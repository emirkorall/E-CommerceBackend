package com.ecommerce.controller;


import com.ecommerce.converter.AddressConverter;
import com.ecommerce.dto.request.AddressRequest;
import com.ecommerce.dto.response.AddressResponse;
import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;
import com.ecommerce.service.AddressService;
import com.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;


    public AddressController(AddressService addressService, UserService userService, AddressConverter addressConverter) {
        this.addressService = addressService;

    }

    @GetMapping

    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        return ResponseEntity.ok(addressService.findAllAddresses());

    }

    @GetMapping("/{id}")

    public ResponseEntity<AddressResponse> getAddressById(@PathVariable long id) {
        return ResponseEntity.ok(addressService.findAddressById(id));
    }

    @PostMapping

    public ResponseEntity<AddressResponse> createAddress(@Valid @RequestBody AddressRequest request,
                                                         @AuthenticationPrincipal User user) {
        AddressResponse response = addressService.saveAddress(request, user);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")

    public ResponseEntity<AddressResponse> updateAddress(@PathVariable long id, @Valid @RequestBody AddressRequest request, @AuthenticationPrincipal User user) {
        AddressResponse response = addressService.updateAddress(id, request, user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<AddressResponse> deleteAddress(@PathVariable long id) {
        return ResponseEntity.ok(addressService.deleteAddressById(id));
    }

}

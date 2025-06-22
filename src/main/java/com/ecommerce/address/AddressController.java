package com.ecommerce.address;


import com.ecommerce.address.dto.AddressRequest;
import com.ecommerce.address.dto.AddressResponse;
import com.ecommerce.user.User;
import com.ecommerce.user.UserService;
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

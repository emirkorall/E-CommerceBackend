package com.ecommerce.address;

import com.ecommerce.address.dto.AddressRequest;
import com.ecommerce.address.dto.AddressResponse;
import com.ecommerce.user.User;
import com.ecommerce.user.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

  private final AddressService addressService;

  public AddressController(
      AddressService addressService, UserService userService, AddressConverter addressConverter) {
    this.addressService = addressService;
  }

  @GetMapping
  public ResponseEntity<List<AddressResponse>> getAllAddresses(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(addressService.findAllAddresses(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddressResponse> getAddressById(
      @PathVariable long id, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(addressService.findAddressById(id, user));
  }

  @PostMapping
  public ResponseEntity<AddressResponse> createAddress(
      @RequestBody AddressRequest request, @AuthenticationPrincipal User user) {
    return ResponseEntity.status(201).body(addressService.saveAddress(request, user));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AddressResponse> updateAddress(
      @PathVariable long id,
      @RequestBody AddressRequest request,
      @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(addressService.updateAddress(id, request, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AddressResponse> deleteAddress(
      @PathVariable long id, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(addressService.deleteAddressById(id, user));
  }
}

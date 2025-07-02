package com.ecommerce.cart;

import com.ecommerce.cart.dto.CartRequest;
import com.ecommerce.cart.dto.CartResponse;
import com.ecommerce.user.User;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping
  public ResponseEntity<List<CartResponse>> getAllCarts(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(cartService.findAllCarts(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CartResponse> getCartById(
      @PathVariable long id, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(cartService.findCartById(id, user));
  }

  @PostMapping
  public ResponseEntity<CartResponse> createCart(
      @RequestBody CartRequest request, @AuthenticationPrincipal User user) {
    return ResponseEntity.status(201).body(cartService.saveCart(request, user));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CartResponse> updateCart(
      @PathVariable long id, @RequestBody CartRequest request, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(cartService.updateCart(id, request, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CartResponse> deleteCart(
      @PathVariable long id, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(cartService.deleteCartById(id, user));
  }
}

package com.ecommerce.cart;

import com.ecommerce.cart.dto.CartItemResponse;
import com.ecommerce.cart.dto.CartResponse;
import com.ecommerce.user.User;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {
  public Cart toEntity(com.ecommerce.cart.dto.CartRequest request, User user) {
    Cart cart = new Cart();
    cart.setUser(user);
    // Optionally, add items from request.items() if you want batch add
    return cart;
  }

  public CartResponse toResponse(Cart cart) {
    List<CartItemResponse> items =
        cart.getItems().stream()
            .map(
                item ->
                    new CartItemResponse(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()))
            .collect(Collectors.toList());
    BigDecimal total =
        items.stream()
            .map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    return new CartResponse(cart.getId(), items, total);
  }

  public void updateEntity(Cart cart, com.ecommerce.cart.dto.CartRequest request, User user) {
    cart.setUser(user);
    // Optionally, update items from request.items() if you want batch update
  }
}

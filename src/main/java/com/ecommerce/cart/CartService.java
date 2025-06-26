package com.ecommerce.cart;

import com.ecommerce.cart.dto.CartRequest;
import com.ecommerce.cart.dto.CartResponse;
import com.ecommerce.user.User;
import java.util.List;

public interface CartService {
  List<CartResponse> findAllCarts(User user);

  CartResponse findCartById(long id, User user);

  CartResponse saveCart(CartRequest request, User user);

  CartResponse updateCart(long id, CartRequest request, User user);

  CartResponse deleteCartById(long id, User user);
}

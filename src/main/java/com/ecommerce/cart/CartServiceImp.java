package com.ecommerce.cart;

import com.ecommerce.cart.dto.CartRequest;
import com.ecommerce.cart.dto.CartResponse;
import com.ecommerce.user.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImp implements CartService {
  private final CartRepository cartRepository;
  private final CartConverter cartConverter;

  public CartServiceImp(CartRepository cartRepository, CartConverter cartConverter) {
    this.cartRepository = cartRepository;
    this.cartConverter = cartConverter;
  }

  @Override
  public List<CartResponse> findAllCarts(User user) {
    if (user.getAuthority().equals("ADMIN")) {
      return cartRepository.findAll().stream()
          .map(cartConverter::toResponse)
          .collect(Collectors.toList());
    } else {
      return cartRepository
          .findByUser(user)
          .map(cart -> List.of(cartConverter.toResponse(cart)))
          .orElse(List.of());
    }
  }

  @Override
  public CartResponse findCartById(long id, User user) {
    Cart cart =
        cartRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    if (!cart.getUser().equals(user) && !user.getAuthority().equals("ADMIN")) {
      throw new RuntimeException("Access denied");
    }
    return cartConverter.toResponse(cart);
  }

  @Override
  public CartResponse saveCart(CartRequest request, User user) {
    Cart cart = cartConverter.toEntity(request, user);
    Cart savedCart = cartRepository.save(cart);
    return cartConverter.toResponse(savedCart);
  }

  @Override
  public CartResponse updateCart(long id, CartRequest request, User user) {
    Cart existingCart =
        cartRepository
            .findById(id)
            .filter(c -> c.getUser().equals(user))
            .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    cartConverter.updateEntity(existingCart, request, user);
    return cartConverter.toResponse(cartRepository.save(existingCart));
  }

  @Override
  public CartResponse deleteCartById(long id, User user) {
    Cart cart =
        cartRepository
            .findById(id)
            .filter(c -> c.getUser().equals(user))
            .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    cartRepository.delete(cart);
    return cartConverter.toResponse(cart);
  }
}

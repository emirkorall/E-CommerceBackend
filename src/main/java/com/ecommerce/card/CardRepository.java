package com.ecommerce.card;

import com.ecommerce.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
  Optional<Card> findByIdAndUser(Long id, User user);

  List<Card> findAllByUser(User user);
}

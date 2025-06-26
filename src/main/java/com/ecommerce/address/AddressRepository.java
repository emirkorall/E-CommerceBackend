package com.ecommerce.address;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.user.User;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByIdAndUser(Long id, User user);
    List<Address> findAllByUser(User user);
}

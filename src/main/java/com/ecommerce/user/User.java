package com.ecommerce.user;

import com.ecommerce.address.Address;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user", schema = "ecommerce")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "surname", nullable = false)
  private String surname;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "authority", nullable = false)
  private String authority;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
  private List<Address> addresses;

  public void addAddress(Address address) {
    if (addresses == null) {
      addresses = new ArrayList<>();
    }
    addresses.add(address);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
    return Collections.singleton(grantedAuthority);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

package com.ecommerce.role;

import com.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role", schema = "ecommerce")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "authority")
  private String authority;

  @OneToMany(
      cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
      mappedBy = "role",
      fetch = FetchType.LAZY)
  @JsonIgnore
  private List<User> users;

  public void addUser(User user) {
    if (users == null) {
      users = new ArrayList<>();
    }
    users.add(user);
  }

  @Override
  public String getAuthority() {
    return authority;
  }
}

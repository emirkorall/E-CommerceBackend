package com.ecommerce.card;

import com.ecommerce.order.Order;
import com.ecommerce.user.User;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "card", schema = "ecommerce")
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "last_four_digits", length = 4, nullable = false)
  private String lastFourDigits;

  @Column(name = "card_no")
  private String cardNo;

  @Column(name = "card_name")
  private String cardName;

  @Column(name = "card_month")
  private String cardMonth;

  @Column(name = "card_year")
  private String cardYear;

  @OneToMany(
      cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinTable(
      name = "card_order",
      schema = "ecommerce",
      joinColumns = @JoinColumn(name = "card_id"),
      inverseJoinColumns = @JoinColumn(name = "order_id"))
  private List<Order> orders;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
}

package com.ecommerce.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order", schema = "ecommerce")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private long addressId;


    private String orderDate;


    @Column(name = "card_no")
    private String cardNo;


    @Column(name = "card_name")
    private String cardName;


    @Column(name = "card_month")
    private String cardMonth;


    @Column(name = "card_year")
    private String cardYear;


    @Column(name = "card_cvv")
    private String cardCvv;


    @Column(name = "price")
    private BigDecimal price;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();
}

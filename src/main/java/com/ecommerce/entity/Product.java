package com.ecommerce.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product", schema = "ecommerce")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column(name = "name")
    private String name;



    @Column(name = "price")
    private BigDecimal price;


    @Column(name = "image")
    private String image;


    @Column(name = "category")
    private String category;


    @Column(name = "rating")
    private double rating;


    @Column(name = "reviews")
    private int reviews;

    @Column(name = "is_new")
    private boolean isNew;

    @ElementCollection
    @Column(name = "features")
    private List<String> features;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "ordered_product", schema = "ecommerce", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }
}

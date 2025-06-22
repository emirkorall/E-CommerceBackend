package com.ecommerce.address;


import com.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "address", schema = "ecommerce")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "title")
    private String title;


    @Column(name = "name")
    private String name;


    @Column(name = "surname")
    private String surname;


    @Column(name = "phone")
    private String phone;


    @Column(name = "city")
    private String city;


    @Column(name = "district")
    private String district;


    @Column(name = "neighborhood")
    private String neighborhood;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

}

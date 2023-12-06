package com.seng315.finalproject.domain.address;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressID;

    @Column(name = "address_one")
    private String addressOne;

    @Column(name = "address_two")
    private String addressTwo;
    private String city;
    private String state;
    private String country;
    private String zip;
}

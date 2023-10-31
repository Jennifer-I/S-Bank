package com.jennifer.sbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long addressId;
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    @JoinColumn
    @OneToOne(mappedBy = "address")
    private Customer customer;


}

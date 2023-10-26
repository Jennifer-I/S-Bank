package com.jennifer.sbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Address {

    @Id
    private Long addressId;

    private String streetNumber;

    private String streetName;
    private String city;
    private String state;
    private String zipCode;

}

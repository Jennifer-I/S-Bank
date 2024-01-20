package com.jennifer.sbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal balance;
    private String phoneNumber;
    private String password;
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private AccountType accountType;
    private String accountNumber;

    public RegistrationRequest (String email, String firstName, String lastName, String phoneNumber, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
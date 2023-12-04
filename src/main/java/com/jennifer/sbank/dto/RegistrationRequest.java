package com.jennifer.sbank.dto;

import com.jennifer.sbank.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
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

}

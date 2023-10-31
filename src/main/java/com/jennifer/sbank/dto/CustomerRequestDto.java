package com.jennifer.sbank.dto;

import com.jennifer.sbank.model.Account;
import com.jennifer.sbank.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;
    private Account account;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

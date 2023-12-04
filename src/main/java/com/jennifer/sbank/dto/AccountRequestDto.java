package com.jennifer.sbank.dto;

import com.jennifer.sbank.enums.AccountType;

import java.time.LocalDateTime;

public class AccountRequestDto {

    private AccountType accountType;
    private LocalDateTime createdAt;
}

package com.jennifer.sbank.model;

import com.jennifer.sbank.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column
    private String AccountNumber;
    private String FirstName;
    private String LastName;
    @Email
    private String email;
    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "sourceAccount")
    private List<Transaction> sourceTransactions;

    @OneToMany(mappedBy = "destinationAccount")
    private List<Transaction> destinationTransactions;



}

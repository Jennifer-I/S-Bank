package com.jennifer.sbank.model;

import com.jennifer.sbank.enums.AccountType;
import jakarta.persistence.*;
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
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column
    private String AccountNumber;
    private String firstName;
    private String lastName;
    private String email;
    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
    @OneToMany(mappedBy = "sourceAccount")
    private List<Transaction> sourceTransactions;

    @OneToMany(mappedBy = "destinationAccount")
    private List<Transaction> destinationTransactions;



}

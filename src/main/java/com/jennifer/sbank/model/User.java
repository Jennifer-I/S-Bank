package com.jennifer.sbank.model;

import com.jennifer.sbank.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;

    @OneToMany(mappedBy = "users")
    private List<Account> account;
    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isVerified;
    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime lastLoginDate;



}

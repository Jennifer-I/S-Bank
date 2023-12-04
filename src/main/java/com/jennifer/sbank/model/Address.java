package com.jennifer.sbank.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "address")
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
    private User user;


}

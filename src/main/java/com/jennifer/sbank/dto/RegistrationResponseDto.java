package com.jennifer.sbank.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponseDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    @JsonIgnore
    private String password;
    private LocalDateTime customerCreationDate;


}

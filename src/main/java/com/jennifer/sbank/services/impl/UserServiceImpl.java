package com.jennifer.sbank.services.impl;

import com.jennifer.sbank.dto.RegistrationRequest;
import com.jennifer.sbank.dto.RegistrationResponseDto;
import com.jennifer.sbank.enums.Role;
import com.jennifer.sbank.model.User;
import com.jennifer.sbank.repositories.AccountRepository;
import com.jennifer.sbank.repositories.AddressRepository;
import com.jennifer.sbank.repositories.UserRepository;
import com.jennifer.sbank.services.UserService;
import com.jennifer.sbank.utils.AccountUtils;
import com.jennifer.sbank.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;
    private final AccountUtils accountUtils;
    private final PasswordEncoder passwordEncoder;



    @Override

    public ApiResponse<RegistrationResponseDto> registerCustomer(RegistrationRequest registrationRequest) {

       User newUser = User.builder()
                .email(registrationRequest.getEmail())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .fullName(registrationRequest.getFirstName() + " " + registrationRequest.getLastName())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(Role.CUSTOMER)
                .createdAt(LocalDateTime.now())

                .build();
        userRepository.save(newUser);

      RegistrationResponseDto build = RegistrationResponseDto.builder()
                .email(registrationRequest.getEmail())
                .fullName(registrationRequest.getFirstName() + " " + registrationRequest.getLastName())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .password(registrationRequest.getPassword())
                .customerCreationDate(LocalDateTime.now())
                .build();

        return new ApiResponse<>(build, "User created successfully", true);
    }


}
package com.jennifer.sbank.services.impl;

import com.jennifer.sbank.dto.LoginRequest;
import com.jennifer.sbank.dto.LoginResponse;
import com.jennifer.sbank.dto.RegistrationRequest;
import com.jennifer.sbank.dto.RegistrationResponseDto;
import com.jennifer.sbank.enums.Role;
import com.jennifer.sbank.exception.UserExistException;
import com.jennifer.sbank.model.User;
import com.jennifer.sbank.repositories.UserRepository;

import com.jennifer.sbank.security.JWTService;
import com.jennifer.sbank.services.UserService;
import com.jennifer.sbank.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;





    @Override

    public ApiResponse<RegistrationResponseDto> registerCustomer(RegistrationRequest registrationRequest) {
        Optional<User> user = userRepository.findByEmail(registrationRequest.getEmail());
        if (user.isEmpty()) {
            User newUser = User.builder()
                    .email(registrationRequest.getEmail())
                    .firstName(registrationRequest.getFirstName())
                    .lastName(registrationRequest.getLastName())
                    .fullName(registrationRequest.getFirstName() + " " + registrationRequest.getLastName())
                    .phoneNumber(registrationRequest.getPhoneNumber())
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .isVerified(true)
                    .role(Role.CUSTOMER)
                    .createdAt(LocalDateTime.now())

                    .build();
            userRepository.save(newUser);

            RegistrationResponseDto response = RegistrationResponseDto.builder()
                    .email(registrationRequest.getEmail())
                    .fullName(registrationRequest.getFirstName() + " " + registrationRequest.getLastName())
                    .phoneNumber(registrationRequest.getPhoneNumber())
                    .password(registrationRequest.getPassword())
                    .customerCreationDate(LocalDateTime.now())
                    .build();
            return new ApiResponse<>("00", "user created successfully", response, "true");

        } else {
            return new ApiResponse<>("01", "email already taken", null, "false");
        }

    }
        public ApiResponse<LoginResponse> login (LoginRequest loginRequest){
            log.info("Request to login user");

            Authentication authentication;
            try {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );
                log.info("Authenticated the User by the Authentication manager");
            } catch (DisabledException es) {
                return Stream.of(
                                new AbstractMap.SimpleEntry<>("message", "Disabled exception occurred"),
                                new AbstractMap.SimpleEntry<>("status", "failure"),
                                new AbstractMap.SimpleEntry<>("httpStatus", HttpStatus.BAD_REQUEST)
                        )
                        .collect(
                                Collectors.collectingAndThen(
                                        Collectors.toMap(AbstractMap.SimpleEntry::getKey, entry -> entry.getValue()),
                                        map -> new ApiResponse<>((Map<String, String>) map)
                                )
                        );

            } catch (BadCredentialsException e) {
                throw new UserExistException("Invalid email or password", HttpStatus.BAD_REQUEST);
            }
            // Tell securityContext that this user is in the context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Retrieve the user from the repository
            User appUser = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                    new UserExistException("User not found", HttpStatus.BAD_REQUEST));
            // Update the lastLoginDate field
            appUser.setLastLoginDate(LocalDateTime.now());
            log.info("last-login date updated");
            // Save the updated user entity
            User user = userRepository.save(appUser);
            log.info("user saved back to database");
            // Generate and send token
            String tokenGenerated = "Bearer " + jwtService.generateToken(authentication, user.getRole());
            log.info("Jwt token generated for the user " + tokenGenerated);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(tokenGenerated);
            ApiResponse<LoginResponse> apiResponse = new ApiResponse<>("00", "Success", loginResponse, "Successfully logged in", HttpStatus.OK);

            apiResponse.setData(loginResponse);

            return apiResponse;
        }




        public Page<User> fetchByRole(Role role, Pageable pageable, String email) {
            return userRepository.findByRole(role, pageable);
        }



    }


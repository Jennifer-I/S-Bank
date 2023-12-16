package com.jennifer.sbank.controller;

import com.jennifer.sbank.dto.LoginRequest;
import com.jennifer.sbank.dto.LoginResponse;
import com.jennifer.sbank.dto.RegistrationRequest;
import com.jennifer.sbank.dto.RegistrationResponseDto;
import com.jennifer.sbank.services.UserService;
import com.jennifer.sbank.utils.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
//@Validated
public class AuthController {
    private final UserService userService;

    @PostMapping("/registerCustomer")
    public ApiResponse<RegistrationResponseDto> registerCustomer(@RequestBody RegistrationRequest registrationRequest) {
        return userService.registerCustomer(registrationRequest);

    }

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse<LoginResponse>> loginUser(@RequestBody LoginRequest request) {
        log.info("request to login user");
        ApiResponse<LoginResponse> response = userService.login(request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


    }




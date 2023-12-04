package com.jennifer.sbank.controller;

import com.jennifer.sbank.dto.RegistrationRequest;
import com.jennifer.sbank.dto.RegistrationResponseDto;
import com.jennifer.sbank.services.UserService;
import com.jennifer.sbank.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customers")

public class CustomerController {
    private final UserService userService;

    @PostMapping("/registerCustomer")
    public ApiResponse<RegistrationResponseDto> registerCustomer(@RequestBody RegistrationRequest registrationRequest) {
        return userService.registerCustomer(registrationRequest);

    }

}

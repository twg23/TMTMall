package com.example.TMTMall.service;

import com.example.TMTMall.dto.CustomerRegistrationRequest;
import com.example.TMTMall.dto.LoginDTO;

public interface CustomerRegistrationService {
    String saveCustomerRegistration(CustomerRegistrationRequest customerRegistrationRequest);

    String customerLogin(LoginDTO loginDTO);

    String receiveToken(String token);
}

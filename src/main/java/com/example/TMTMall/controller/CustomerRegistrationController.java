package com.example.TMTMall.controller;

import com.example.TMTMall.dto.CustomerRegistrationRequest;
import com.example.TMTMall.dto.LoginDTO;
import com.example.TMTMall.service.CustomerRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/register")
public class CustomerRegistrationController {
     CustomerRegistrationService customerRegistrationService;

     @PostMapping("/user")
    public String CustomerRegistration(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
         return customerRegistrationService.saveCustomerRegistration(customerRegistrationRequest);
     }

     @PostMapping("/login")
     public String customerLogging(@RequestBody LoginDTO loginDTO){
         return customerRegistrationService.customerLogin(loginDTO);
     }

     @GetMapping("/token")
     public String viewDummyEndpoint(@RequestHeader String authorisation){
         String token = authorisation.substring(7);
         return customerRegistrationService.receiveToken(token);
     }

     @Autowired
    public CustomerRegistrationController(CustomerRegistrationService customerRegistrationService) {
        this.customerRegistrationService = customerRegistrationService;
    }
}

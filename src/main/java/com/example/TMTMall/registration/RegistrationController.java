package com.example.TMTMall.registration;

import com.example.TMTMall.registration.RegistrationRequest;
import com.example.TMTMall.registration.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;
    @PostMapping
    public String registration (@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}

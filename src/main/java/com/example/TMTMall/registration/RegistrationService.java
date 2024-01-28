package com.example.TMTMall.registration;

import com.example.TMTMall.registration.EmailValidator;
import com.example.TMTMall.registration.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
 private EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
      boolean isValidEmail = emailValidator.
              test(request.getEmail());
      if (!isValidEmail){
         throw new IllegalStateException("email not valid");
      }
        return "works";
    }
}

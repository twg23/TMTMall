package com.example.TMTMall.service;

import com.example.TMTMall.dto.CustomerRegistrationRequest;
import com.example.TMTMall.dto.LoginDTO;
import com.example.TMTMall.model.CustomerRegistration;
import com.example.TMTMall.repository.RegistrationRepository;
import com.example.TMTMall.util.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private RegistrationRepository registrationRepository;
    private PasswordEncoder passwordEncoder;

    private JWTTokenUtil jwtTokenUtil;
    String namePattern ="^[a-zA-Z]+\\s[a-zA-Z]+$";
    String  passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Autowired
    public CustomerRegistrationServiceImpl(RegistrationRepository registrationRepository, PasswordEncoder passwordEncoder, JWTTokenUtil jwtTokenUtil) {
        this.registrationRepository = registrationRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }



    @Override
    public String saveCustomerRegistration(CustomerRegistrationRequest customerRegistrationRequest){
        if (!customerRegistrationRequest.getName().matches(namePattern)){
            throw new ResponseStatusException(HttpStatusCode.valueOf(422), "Please enter your first and last name");

        }
        if (!customerRegistrationRequest.getEmail().matches(emailPattern)){
            log.info("email validation");
            throw  new ResponseStatusException(HttpStatusCode.valueOf(422),"You have entered an invalid email");

        }

         if (!customerRegistrationRequest.getPassword().matches(passwordPattern)){
             log.info("Password validation");
             throw new ResponseStatusException(HttpStatusCode.valueOf(422), "Your password requires one symbol, upper case and a special character");
         }

        String encodedPassword = passwordEncoder.encode(customerRegistrationRequest.getPassword());

        CustomerRegistration customerRegistration = new CustomerRegistration();
        customerRegistration.setName(customerRegistrationRequest.getName());
        customerRegistration.setEmail(customerRegistrationRequest.getEmail());
        customerRegistration.setPassword(encodedPassword);
        registrationRepository.save(customerRegistration);
        return "You have been registerd";


    }

    @Override
    public String customerLogin(LoginDTO loginDTO){

       CustomerRegistration customer = registrationRepository.findCustomerRegistrationByEmail(loginDTO.getEmail());
        if (customer == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(422), "No customer found for this email");
        }



        if (!passwordEncoder.matches(loginDTO.getPassword(), customer.getPassword())){
            log.debug("validate password is on the database");
            throw new ResponseStatusException(HttpStatusCode.valueOf(422), "Your password  is not correct");
        }

       String token = jwtTokenUtil.generateToken(customer.getEmail());

        return token;
    }

    @Override
    public String receiveToken(String token){
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        CustomerRegistration customer = registrationRepository.findCustomerRegistrationByEmail(userName);
        boolean isTokenValid = jwtTokenUtil.validateToken(token,customer);
        if (isTokenValid){
            return "token is valid";
        }
        return "token is not valid";
    }



}

package com.example.TMTMall.repository;

import com.example.TMTMall.model.CustomerRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends CrudRepository<CustomerRegistration, Long>{

   CustomerRegistration findCustomerRegistrationByEmail(String email);
}

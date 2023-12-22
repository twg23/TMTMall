package com.example.TMTMall.service;

import com.example.TMTMall.dto.CustomerDTO;
import com.example.TMTMall.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerDTO customerDTO);

    void deleteCutomer(Long id);

    Customer updateCustomerDetails(Long id, CustomerDTO customerDTO);

    List<Customer> findAllCustomer();

    Customer findCustomerById(Long id);
}

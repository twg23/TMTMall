package com.example.TMTMall.controller;

import com.example.TMTMall.dto.CustomerDTO;
import com.example.TMTMall.exception.IdNotFoundException;
import com.example.TMTMall.model.Customer;
import com.example.TMTMall.service.CustomerService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/create")
    public Customer createCustomer( @RequestBody CustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCutomer(@PathVariable Long id){
        customerService.deleteCutomer(id);
    }

    @PutMapping("/update")
    public Customer updateCustomerDetails(Long id, CustomerDTO customerDTO) {
        return customerService.updateCustomerDetails(id, customerDTO);
    }

    @GetMapping("/find/all")
    public List<Customer> findAllCustomer(){
     return   customerService.findAllCustomer();
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }

}

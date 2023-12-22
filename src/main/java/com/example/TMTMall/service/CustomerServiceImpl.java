package com.example.TMTMall.service;

import com.example.TMTMall.dto.AddressDTO;
import com.example.TMTMall.dto.CustomerDTO;
import com.example.TMTMall.exception.IdNotFoundException;
import com.example.TMTMall.model.Address;
import com.example.TMTMall.model.Customer;
import com.example.TMTMall.repository.AddressRepository;
import com.example.TMTMall.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;

    @Override
    public Customer createCustomer(CustomerDTO customerDTO){

        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .emailAddress(customerDTO.getEmailAddress())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();

        Customer savedCustomer =  customerRepository.save(customer);

        Address address= new Address();
        address.setLineOne(customerDTO.getAddress().getLineOne());
        address.setCity(customerDTO.getAddress().getCity());
        address.setCountry(customerDTO.getAddress().getCountry());
        address.setPostCode(customerDTO.getAddress().getPostCode());
        address.setCustomer(savedCustomer);
        Address savedAddress = addressRepository.save(address);



      return  savedCustomer;
    }

    @Override
    public void deleteCutomer(Long id){
       Customer customer = customerRepository.findById(id).orElseThrow(()
               -> new IdNotFoundException ("Customer ID not found"));
        customerRepository.delete(customer);
    }

    @Override
    public Customer updateCustomerDetails(Long id, CustomerDTO customerDTO){
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new IdNotFoundException ("Customer ID not found"));
        customer.setName(customerDTO.getName());
       // customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmailAddress(customerDTO.getEmailAddress());

     return  customerRepository.save(customer);

    }

    @Override
    public List<Customer> findAllCustomer(){
        List<Customer> customerList = new ArrayList<>();
        Iterable<Customer> customerIts = customerRepository.findAll();
        customerIts.forEach(customerList :: add);
        return customerList;
    }

    @Override
    public Customer findCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new IdNotFoundException ("Customer ID not found"));
        return customer;
    }

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }
}

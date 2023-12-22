package com.example.TMTMall.repository;

import com.example.TMTMall.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public Customer findByName(String name);
}

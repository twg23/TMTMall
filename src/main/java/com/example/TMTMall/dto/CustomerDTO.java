package com.example.TMTMall.dto;

import com.example.TMTMall.model.Address;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
public class CustomerDTO {

    private String name;

    private AddressDTO address;
    private  String phoneNumber;
    private String emailAddress;


}

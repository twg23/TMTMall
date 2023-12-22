package com.example.TMTMall.dto;

import com.example.TMTMall.model.Customer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AddressDTO {

    private String lineOne;
    private String city;
    private String postCode;
    private String country;

}

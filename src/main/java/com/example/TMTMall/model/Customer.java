package com.example.TMTMall.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity for anytype od sequel server (e.g oracle, mySQL , postgreS)/ sequence h2 / auto when unsure=this is only valid for sql database can only be used with mysql
    private long id;
    private String name; /*split name*/
    private  String phoneNumber;
    private String emailAddress;


    @JsonManagedReference
    @OneToMany (mappedBy =  "customer", cascade = CascadeType.ALL)
    private List<Order> order;


}

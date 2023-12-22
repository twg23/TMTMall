package com.example.TMTMall.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity for anytype od sequel server (e.g oracle, mySQL , postgreS)/ sequence h2 / auto when unsure=this is only valid for sql database can only be used with mysql
    private long id;
    @Enumerated (EnumType.STRING)
    private ProductCategory category;
    private String name;
    private double price;
    private int quantity;


}

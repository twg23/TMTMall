package com.example.TMTMall.dto;

import com.example.TMTMall.model.ProductCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {

    private String category;
    private String name;
    private double price;
    private int quantity;



}

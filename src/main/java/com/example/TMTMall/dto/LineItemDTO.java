package com.example.TMTMall.dto;

import lombok.Data;

@Data
public class LineItemDTO {
    private String productName;
    private  int quantity;
    private String deliveryAddress;
}

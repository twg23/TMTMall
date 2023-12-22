package com.example.TMTMall.dto;

import com.example.TMTMall.model.LineItem;
import lombok.Data;

import java.util.List;
@Data
public class OrderDTO {
    private List<LineItemDTO> lineItemDTO;
    private String customerName;
}

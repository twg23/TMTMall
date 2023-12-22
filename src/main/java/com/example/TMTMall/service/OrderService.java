package com.example.TMTMall.service;

import com.example.TMTMall.dto.OrderDTO;
import com.example.TMTMall.model.LineItem;
import com.example.TMTMall.model.Order;

import java.util.List;

public interface OrderService {
    String createOrder(OrderDTO orderDTO);

    List<Order> findAllOrders();

    List<LineItem> findOrderById(Long id);
}

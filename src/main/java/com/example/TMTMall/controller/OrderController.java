package com.example.TMTMall.controller;

import com.example.TMTMall.dto.LineItemDTO;
import com.example.TMTMall.dto.OrderDTO;
import com.example.TMTMall.model.Customer;
import com.example.TMTMall.model.LineItem;
import com.example.TMTMall.model.Order;
import com.example.TMTMall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

   @PostMapping ("/create")
    public String createOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
        return "Order created successfully";
    }

    @GetMapping("/find")
    public List<Order> findAllOrders(){
       return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public List<LineItem> findOrderById(@PathVariable Long id){
        return orderService.findOrderById(id);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<LineItem>> findOrderById(@PathVariable Long id){
//        return ResponseEntity.ok(orderService.findOrderById(id));
//    }
}

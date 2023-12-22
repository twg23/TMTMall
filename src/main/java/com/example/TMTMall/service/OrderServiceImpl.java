package com.example.TMTMall.service;

import com.example.TMTMall.dto.LineItemDTO;
import com.example.TMTMall.dto.OrderDTO;
import com.example.TMTMall.exception.IdNotFoundException;
import com.example.TMTMall.model.Customer;
import com.example.TMTMall.model.LineItem;
import com.example.TMTMall.model.Order;
import com.example.TMTMall.model.Product;
import com.example.TMTMall.repository.CustomerRepository;
import com.example.TMTMall.repository.LineItemRepository;
import com.example.TMTMall.repository.OrderRepository;
import com.example.TMTMall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private LineItemRepository lineItemRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Override
    public String createOrder(OrderDTO orderDTO){

        Customer customer = customerRepository.findByName(orderDTO.getCustomerName());
        Order order = new Order();
        order.setCustomer(customer);
        Order savedOrder = orderRepository.save(order);

        for(LineItemDTO lineItemDTO: orderDTO.getLineItemDTO()) {
            LineItem lineItem = new LineItem();

            lineItem.setOrder(savedOrder);
            lineItem.setProductName(lineItemDTO.getProductName());
            lineItem.setQuantity(lineItemDTO.getQuantity());
            lineItem.setDeliveryAddress(lineItemDTO.getDeliveryAddress());

           List<Product> product = productRepository.findByNameIgnoreCaseAndTrim(lineItem.getProductName());

            if(product != null) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,("Product is null"));
            }

            if (lineItem.getQuantity() <= product.get(0).getQuantity()) {
                product.get(0).setQuantity(product.get(0).getQuantity() - lineItem.getQuantity());
                productRepository.save(product.get(0));
                lineItemRepository.save(lineItem);
            }
            else {
            throw new RuntimeException("Out of stock for product: " + lineItem.getProductName());
            }
         //   lineItemRepository.save(lineItem);

        }


        return "Order created successfully";

    }

    @Override
    public List<Order> findAllOrders(){
        List<Order> orders = new ArrayList<>();
        Iterable<Order> orderIts =  orderRepository.findAll();
        orderIts.forEach(orders :: add);

        return orders;
    }


//    public  List<LineItem> findOrderById(Long id){
//        List<LineItem> lineItems = lineItemRepository.findLineItemByOrder_Id(id);
//
//       return lineItems;
//
//    }

    @Override
    public List<LineItem> findOrderById(Long id){
       Order order = orderRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Id not found"));
       order.setLineItems(lineItemRepository.findLineItemByOrder_Id(id));
       return order.getLineItems();

    }




//    @Override
//    public String createOrder(LineItemDTO lineItemDTO){
//
//        Customer customer = customerRepository.findByName(lineItemDTO.getName());
//         Order order = new Order();
//         order.setCustomer(customer);
//         order.setLineItems();
//         orderRepository.save(order);
//
//
//
//
//        LineItem lineItem = new LineItem();
//        lineItem.setOrder(order);
//        lineItem.setProductName(lineItemDTO.getProductName());
//        lineItem.setQuantity(lineItemDTO.getQuantity());
//        lineItem.setDeliveryAddress(lineItemDTO.getDeliveryAddress());
//
//        order.getLineItems().add(lineItem);
//
//
//        lineItemRepository.save(lineItem);
//
//        //orderRepository.save(order);
//
//        return "Order created successfully";
//
//    }
//
//    @Override
//    public List<Order> findAllOrders(){
//        List<Order> orders = new ArrayList<>();
//        Iterable<Order> orderIts =  orderRepository.findAll();
//        orderIts.forEach(orders :: add);
//
//       return orders;
//    }




    @Autowired

    public OrderServiceImpl(LineItemRepository lineItemRepository, CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.lineItemRepository = lineItemRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
}

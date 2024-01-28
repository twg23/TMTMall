package com.example.TMTMall.service;

import com.example.TMTMall.dto.LineItemDTO;
import com.example.TMTMall.dto.OrderDTO;
import com.example.TMTMall.exception.IdNotFoundException;
import com.example.TMTMall.exception.OutOfStockException;
import com.example.TMTMall.exception.ProductNotFoundException;
import com.example.TMTMall.model.Customer;
import com.example.TMTMall.model.LineItem;
import com.example.TMTMall.model.Order;
import com.example.TMTMall.model.Product;
import com.example.TMTMall.repository.CustomerRepository;
import com.example.TMTMall.repository.LineItemRepository;
import com.example.TMTMall.repository.OrderRepository;
import com.example.TMTMall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        List<LineItem> lineItems = new ArrayList<>();

        List<Product> productList = new ArrayList<>();

        for(LineItemDTO lineItemDTO: orderDTO.getLineItemDTO()) {
            Product product = productRepository.findByNameIgnoreCaseAndTrim(lineItemDTO.getProductName());

            if(product == null) {
                throw new ProductNotFoundException("Product not found " + lineItemDTO.getProductName());
            }

           if (lineItemDTO.getQuantity() > product.getQuantity()){
               throw new OutOfStockException("The quantity requested  is more than what is available for  "
                       + lineItemDTO.getProductName() +"\n" +
                       "This is the quantity that is available " + product.getQuantity());
           }

            LineItem lineItem = new LineItem();
            lineItem.setProductName(lineItemDTO.getProductName());
            lineItem.setQuantity(lineItemDTO.getQuantity());
            lineItem.setDeliveryAddress(lineItemDTO.getDeliveryAddress());

            lineItems.add(lineItem);
            product.setQuantity(product.getQuantity() - lineItem.getQuantity());
            productList.add(product);
        }


        productRepository.saveAll(productList);
        Order savedOrder = orderRepository.save(order);
        for(LineItem  lineItem: lineItems){
            lineItem.setOrder(savedOrder);
            lineItemRepository.save(lineItem);
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


    @Override
    public List<LineItem> findOrderById(Long id){
       Order order = orderRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Id not found"));
       order.setLineItems(lineItemRepository.findLineItemByOrder_Id(id));
       return order.getLineItems();

    }

    @Override
    public void deleteOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Id not found"));
        orderRepository.deleteById(order.getId());
    }









    @Autowired
    public OrderServiceImpl(LineItemRepository lineItemRepository, CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.lineItemRepository = lineItemRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
}

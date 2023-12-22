package com.example.TMTMall.service;

import com.example.TMTMall.dto.ProductDTO;
import com.example.TMTMall.exception.IdNotFoundException;
import com.example.TMTMall.model.Product;
import com.example.TMTMall.model.ProductCategory;
import com.example.TMTMall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;

    @Override
    public List<Product> findProduct(){
        List<Product> products = new ArrayList<>();
        Iterable<Product> productsIts = productRepository.findAll();
        productsIts.forEach(products::add);

        return products;

    }


    @Override
    public String createProduct(ProductDTO productDTO){
        Product product = Product.builder()
                .category(ProductCategory.valueOf(productDTO.getCategory()))
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .build();

        productRepository.save(product);

        return "You have saved the product";}


    @Override
    public void deleteProduct(Long id){
       Product product = productRepository.findById(id).orElseThrow(()->
               new IdNotFoundException( "Product ID not found"));
        productRepository.delete(product);
    }

    @Override
    public String  updateProduct(Long id, ProductDTO productDTO){


        Product product = productRepository.findById(id).orElseThrow(()->
                new IdNotFoundException( "Product ID not found"));

       product.setCategory(ProductCategory.valueOf(productDTO.getCategory()));
       product.setName(productDTO.getName());
       product.setPrice(productDTO.getPrice());
       product.setQuantity(productDTO.getQuantity());

        productRepository.save(product);

        return "Your Product has been updated";

    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


}

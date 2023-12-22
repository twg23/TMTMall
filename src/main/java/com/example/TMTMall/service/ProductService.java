package com.example.TMTMall.service;

import com.example.TMTMall.dto.ProductDTO;
import com.example.TMTMall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProduct();

    String createProduct(ProductDTO productDTO);

    void deleteProduct(Long id);

    String updateProduct(Long id, ProductDTO productDTO);
}

package com.example.TMTMall.service;

import com.example.TMTMall.dto.ProductDTO;
import com.example.TMTMall.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findProduct();

    String createProduct(ProductDTO productDTO);

    Page<Product> getProductByPage(Pageable page);

    void deleteProduct(Long id);

    List<Product> sortProductByPrice(String sortType);

    String updateProduct(Long id, ProductDTO productDTO);
}

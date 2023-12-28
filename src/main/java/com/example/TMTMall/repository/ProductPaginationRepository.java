package com.example.TMTMall.repository;

import com.example.TMTMall.model.Product;
import com.example.TMTMall.model.ProductCategory;
import org.hibernate.query.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.awt.print.Pageable;

public interface ProductPaginationRepository extends PagingAndSortingRepository<Product, Long> {

        // Custom query to find products by category
        Page<Product> findByCategory(ProductCategory category, Pageable pageable);

}

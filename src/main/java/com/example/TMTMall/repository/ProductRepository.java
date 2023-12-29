package com.example.TMTMall.repository;


import com.example.TMTMall.dto.LineItemDTO;
import com.example.TMTMall.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

//    @Query(value ="SELECT * FROM products p WHERE p.name= ?1", nativeQuery = true)
//   Product findProductByName (String name);

   // Product findProductsByProductName(String Name);

//    @Query("SELECT p FROM Product p WHERE lower(trim(p.name)) = lower(trim(:name))")
//List<Product> findByNameIgnoreCaseAndTrim(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE lower(replace (p.name, ' ','')) = lower(replace(:name,' ',''))")
    Product findByNameIgnoreCaseAndTrim(@Param("name") String name);

    Page<Product> getAllProduct(Pageable page);



}

package com.example.TMTMall.controller;

import com.example.TMTMall.dto.ProductDTO;
import com.example.TMTMall.exception.IdNotFoundException;
import com.example.TMTMall.model.Product;
import com.example.TMTMall.model.ProductCategory;
import com.example.TMTMall.repository.ProductRepository;
import com.example.TMTMall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
     private ProductService productService;

     @PostMapping("/create")
     public String createProduct (@RequestBody ProductDTO productDTO){
          productService.createProduct(productDTO);
          return "You have created a new Product in the Inventory";
     }

   @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
       productService.deleteProduct(id);
   }

   @PutMapping("/update/{id}")
    public String  updateProduct( @PathVariable Long id, @RequestBody ProductDTO productDTO){
         productService.updateProduct(id, productDTO);
        return "Your Product has been updated";
    }
    @GetMapping("/find")
    public List<Product> findProduct(){
       return productService.findProduct();
    }

    @GetMapping("")
    public Page<Product> getProductByPage(@RequestParam Integer pageSize, @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, pageSize);
       return productService.getProductByPage(pageable);
     }




    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }
}

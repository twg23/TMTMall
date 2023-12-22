package com.example.TMTMall.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LineItem")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity for anytype od sequel server (e.g oracle, mySQL , postgreS)/ sequence h2 / auto when unsure=this is only valid for sql database can only be used with mysql
    private long id;

    @JsonBackReference // indicates that the annotated property (or field) should be ignored during serialization to prevent a circular reference.
    @ManyToOne
    @JoinColumn (name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    private String productName;
    private  int quantity;
    private String deliveryAddress;

}

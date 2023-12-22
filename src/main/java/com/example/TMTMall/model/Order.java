package com.example.TMTMall.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity for anytype od sequel server (e.g oracle, mySQL , postgreS)/ sequence h2 / auto when unsure=this is only valid for sql database can only be used with mysql
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;


    @JsonManagedReference // indicates that the annotated property (or field) is the one that should be included in the JSON output during serialization.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<LineItem> lineItems = new ArrayList<>();


//    @OneToOne
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
//    private Address address;

}

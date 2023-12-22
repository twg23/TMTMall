package com.example.TMTMall.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity for anytype od sequel server (e.g oracle, mySQL , postgreS)/ sequence h2 / auto when unsure=this is only valid for sql database can only be used with mysql
    private long id;
    private String lineOne;
    private String city;
    private String postCode;
    private String country;

    @NonNull
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
}

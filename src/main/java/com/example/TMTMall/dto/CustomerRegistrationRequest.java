package com.example.TMTMall.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NonNull;

@Data
public class CustomerRegistrationRequest {

    private String name;
    private String email;
    private String password;
}

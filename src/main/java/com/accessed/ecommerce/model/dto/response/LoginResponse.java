package com.accessed.ecommerce.model.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private UserResponse user;
}

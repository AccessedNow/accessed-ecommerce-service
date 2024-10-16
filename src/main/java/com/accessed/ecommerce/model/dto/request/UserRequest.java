package com.accessed.ecommerce.model.dto.request;

import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private Set<String> roles;

}

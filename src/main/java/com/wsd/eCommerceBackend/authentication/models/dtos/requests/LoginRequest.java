package com.wsd.eCommerceBackend.authentication.models.dtos.requests;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginRequest {
    private String phoneOrEmail;
    private String password;
}

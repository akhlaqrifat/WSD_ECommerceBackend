package com.wsd.eCommerceBackend.authentication.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private Long id;
    private String username;
    private String email;
    private String userFullName;
    private String phone;
}

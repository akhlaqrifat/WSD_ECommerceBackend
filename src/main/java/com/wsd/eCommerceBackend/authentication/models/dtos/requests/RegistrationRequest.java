package com.wsd.eCommerceBackend.authentication.models.dtos.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class RegistrationRequest {
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String role;
}

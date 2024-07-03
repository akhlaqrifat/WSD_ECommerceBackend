package com.wsd.eCommerceBackend.authentication.models.dtos.responses;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private Long userId;
    private String username;
    private String email;
    private String userPhoto;
    private String role;
    private String useFullName;
    private boolean isNewUser;
}

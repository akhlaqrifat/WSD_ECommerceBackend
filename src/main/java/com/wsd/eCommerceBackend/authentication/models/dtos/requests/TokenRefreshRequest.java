package com.wsd.eCommerceBackend.authentication.models.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TokenRefreshRequest {
    @Schema
    private String refreshToken;
    @Schema
    private Long userId;
}

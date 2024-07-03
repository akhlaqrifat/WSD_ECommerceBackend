package com.wsd.eCommerceBackend.authentication.models.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChangePasswordRequest {
    @Schema
    private String oldPassword;
    @Schema
    private String newPassword;
    @Schema
    private Long userId;

}

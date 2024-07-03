package com.wsd.eCommerceBackend.authentication.models.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutResponse {
    @Schema()
    private String status;
}

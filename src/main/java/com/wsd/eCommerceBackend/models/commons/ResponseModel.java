package com.wsd.eCommerceBackend.models.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseModel<T> {
    @Schema(description = "Response data object or list of objects")
    private T data;

    @Schema(description = "message if any")
    private String message;

    @Schema(description = "error if any")
    private String errorMessage;
}

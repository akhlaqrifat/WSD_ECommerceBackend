package com.wsd.eCommerceBackend.sales.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopProductResponse {
    List<String> productNames;
}

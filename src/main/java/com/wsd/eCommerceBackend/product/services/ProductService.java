package com.wsd.eCommerceBackend.product.services;

import com.wsd.eCommerceBackend.product.models.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProduct(Long id);
}

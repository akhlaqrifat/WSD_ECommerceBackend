package com.wsd.eCommerceBackend.product.services;

import com.wsd.eCommerceBackend.product.models.entity.Product;
import com.wsd.eCommerceBackend.product.repositories.CategoryRepository;
import com.wsd.eCommerceBackend.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }
}

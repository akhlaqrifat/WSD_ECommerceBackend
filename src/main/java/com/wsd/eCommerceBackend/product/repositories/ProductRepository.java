package com.wsd.eCommerceBackend.product.repositories;

import com.wsd.eCommerceBackend.product.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

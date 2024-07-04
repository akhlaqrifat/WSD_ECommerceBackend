package com.wsd.eCommerceBackend.product.repositories;

import com.wsd.eCommerceBackend.product.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}

package com.wsd.eCommerceBackend.sales.repositories;

import com.wsd.eCommerceBackend.sales.models.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByCustomerId(Long customerId);
}

package com.wsd.eCommerceBackend.sales.models.entity;

import com.wsd.eCommerceBackend.authentication.models.entities.User;
import com.wsd.eCommerceBackend.models.commons.MasterEntity;
import com.wsd.eCommerceBackend.product.models.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wishlist")
public class WishList extends MasterEntity {
    @OneToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToMany
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}

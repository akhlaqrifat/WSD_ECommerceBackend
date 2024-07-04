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
@Table(name = "sale")
public class Sale extends MasterEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToMany
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private int productCount;
    private Double paidAmount;
}

package com.wsd.eCommerceBackend.product.models.entity;

import com.wsd.eCommerceBackend.models.commons.MasterEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends MasterEntity {
    private String name;
}

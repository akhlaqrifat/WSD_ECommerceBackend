package com.wsd.eCommerceBackend.authentication.models.entities;

import com.wsd.eCommerceBackend.models.commons.MasterEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role extends MasterEntity {
    private String name;
}

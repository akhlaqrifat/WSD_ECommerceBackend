package com.wsd.eCommerceBackend.authentication.models.entities;

import com.wsd.eCommerceBackend.models.commons.MasterEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "refresh_token")
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends MasterEntity {
    private String token;
    private Long userId;
}

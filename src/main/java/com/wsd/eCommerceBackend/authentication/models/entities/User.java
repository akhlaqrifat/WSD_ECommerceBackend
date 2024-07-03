package com.wsd.eCommerceBackend.authentication.models.entities;

import com.wsd.eCommerceBackend.models.commons.MasterEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User extends MasterEntity implements UserDetails {
    private String userName;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String userPhoto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private boolean doRoleMatch(String roleName) {
        return role.getName().equals(roleName);
    }

    public boolean isAdmin() {
        return doRoleMatch("ROLE_ADMIN");
    }
    public boolean isSuperAdmin() {
        return doRoleMatch("ROLE_SUPER_ADMIN");
    }
    public boolean isUser() {
        return doRoleMatch("ROLE_USER");
    }
}

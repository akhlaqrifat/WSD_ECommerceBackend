package com.wsd.eCommerceBackend.authentication.repositories;

import com.wsd.eCommerceBackend.authentication.models.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    void deleteByUserId(Long userId);
    @Modifying
    @Query("update RefreshToken set token =:token where userId =:userId")
    void updateByUserId(Long userId,String token);

    boolean existsByUserIdAndToken(Long userId,String token);
}

package com.wsd.eCommerceBackend.authentication.repositories;

import com.wsd.eCommerceBackend.authentication.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);

    @Query("select u " +
            "from User u " +
            "where " +
            "( u.phone = :phoneOrEmail ) " +
            "or " +
            "( u.email =:phoneOrEmail )")
    Optional<User> findByPhoneOrEmail(@Param("phoneOrEmail") String phoneOrEmail);
}

package com.spring.jwt.repository;

import com.spring.jwt.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByResetPasswordToken(String token);

    Optional<User> findByMobileNumber(@Param("mobileNumber") Long mobileNumber);

    @Query("SELECT u FROM User u")
    @EntityGraph(attributePaths = {"roles"})
    Page<User> findAllWithRoles(Pageable pageable);

}

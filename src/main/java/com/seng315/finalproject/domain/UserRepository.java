package com.seng315.finalproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query(value = "SELECT COUNT(userID) FROM User where role = 'USER'")
    Integer getUserCount();

    @Query(value = "SELECT COUNT(userID) FROM User where role = 'ADMIN'")
    Integer getAdminCount();

    @Query(value = "SELECT COUNT(user_id) FROM User where created_at BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW()", nativeQuery = true)
    Integer getNewUserCountMonthly();

    @Query(value = "SELECT COUNT(user_id) FROM User where created_at BETWEEN DATE_SUB(NOW(), INTERVAL 90 DAY) AND NOW()", nativeQuery = true)
    Integer getNewUserCountTriMonthly();

    @Query(value = "SELECT COUNT(user_id) FROM User where created_at BETWEEN DATE_SUB(NOW(), INTERVAL 365 DAY) AND NOW()", nativeQuery = true)
    Integer getNewUserCountYearly();
}

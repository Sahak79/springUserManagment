package com.epam.repository;

import com.epam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sahak_Babayan on 3/29/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(value = "SELECT IF(COUNT(id) = 0, 'false', 'true') FROM user WHERE email = :email", nativeQuery = true)
    boolean isEmailExist(@Param("email") String email);

    @Query(value = "SELECT IF(COUNT(id) = 0, 'false', 'true') FROM user WHERE email = :email AND id = :excludedUserID", nativeQuery = true)
    boolean isEmailExist(@Param(value = "email") String email, @Param(value = "excludedUserID") long excludedUserID);

    List<User> findByIdNotIn(List<Long> excludedUserIDs);

}

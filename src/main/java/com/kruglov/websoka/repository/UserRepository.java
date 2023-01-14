package com.kruglov.websoka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kruglov.websoka.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByLogin(String login);

    @Query(value ="SELECT exists (SELECT * FROM users WHERE login = :login)", nativeQuery = true)
    boolean checkExistenceByLogin(@Param(value = "login") String login);

}

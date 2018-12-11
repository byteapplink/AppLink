package com.dexfun.recommend.repository;

import com.dexfun.recommend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUsernameEqualsAndPasswordEquals(String username, String password);
    UserEntity findByUsernameEquals(String username);
}

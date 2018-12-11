package com.dexfun.recommend.service;


import com.dexfun.recommend.entity.UserEntity;

public interface UserService {

    UserEntity findByUsernameEqualsAndPasswordEquals(String username, String password);

    UserEntity findByUserId(Long userId);

    Boolean register(String username, String nickname, String password);
}

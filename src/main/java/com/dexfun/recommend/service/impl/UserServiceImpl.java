package com.dexfun.recommend.service.impl;

import java.sql.Timestamp;

import com.dexfun.recommend.entity.UserEntity;
import com.dexfun.recommend.repository.UserRepository;
import com.dexfun.recommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findByUsernameEqualsAndPasswordEquals(String username, String password) {
        return userRepository.findByUsernameEqualsAndPasswordEquals(username, password);
    }

    @Override
    public UserEntity findByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public Boolean register(String username, String nickname, String password) {
        UserEntity byUsernameEquals = userRepository.findByUsernameEquals(username);
        if (byUsernameEquals == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity.setNickname(nickname);
            userEntity.setStatus(0);
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

}

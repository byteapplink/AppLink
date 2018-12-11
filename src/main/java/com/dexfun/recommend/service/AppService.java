package com.dexfun.recommend.service;

import com.dexfun.recommend.entity.AppEntity;

import java.util.List;

public interface AppService {

    List<AppEntity> findAllByUserId(Long userId);

    List<AppEntity> findAll();

    AppEntity findAllByUserIdAndAppId(Long userId, Long appId);

    void save(AppEntity appEntity);

    void delete(Long userId, Long appId);
}

package com.dexfun.recommend.service.impl;

import com.dexfun.recommend.entity.AppEntity;
import com.dexfun.recommend.repository.AppRepository;
import com.dexfun.recommend.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    AppRepository appRepository;

    @Override
    public List<AppEntity> findAllByUserId(Long userId) {
        return appRepository.findAllByUserIdAndStatusEqualsOrderBySortValueDescAppIdAsc(userId, 0);
    }

    @Override
    public List<AppEntity> findAll() {
        return appRepository.findAllByOrderByAppIdAscSizeDesc();
    }

    @Override
    public AppEntity findAllByUserIdAndAppId(Long userId, Long appId) {
        return appRepository.findAllByUserIdAndAppId(userId, appId);
    }

    @Override
    public void save(AppEntity appEntity) {
        appRepository.save(appEntity);
    }

    @Override
    public void delete(Long userId, Long appId) {
        AppEntity allByUserIdAndAppId = appRepository.findAllByUserIdAndAppId(userId, appId);
        allByUserIdAndAppId.setStatus(-1);//当然是假的删除咯
        appRepository.save(allByUserIdAndAppId);
    }
}

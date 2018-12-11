package com.dexfun.recommend.repository;

import com.dexfun.recommend.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, Long> {

    List<AppEntity> findAllByUserIdAndStatusEqualsOrderBySortValueDescAppIdAsc(Long userId, Integer status);

    AppEntity findAllByUserIdAndAppId(Long userId, Long appId);

    List<AppEntity> findAllByOrderByAppIdAscSizeDesc();

    AppEntity findByUserIdEqualsAndAppIdEquals(Long userId, Long appId);

}

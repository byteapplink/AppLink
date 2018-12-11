package com.dexfun.recommend.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "apps")
public class AppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appId;
    private Long userId;
    private String title;
    private String description;
    private BigDecimal size;
    private String version;
    private String icon;
    private String download;
    private Integer sortValue;
    private Integer status;
    private Timestamp updatedAt;
    private Timestamp createdAt;

}

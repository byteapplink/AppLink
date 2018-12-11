package com.dexfun.recommend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user")
public class UserEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private Integer status;
    private Timestamp updatedAt;
    private Timestamp createdAt;


}

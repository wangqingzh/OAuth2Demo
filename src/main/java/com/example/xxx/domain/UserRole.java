package com.example.xxx.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户角色关系表
 */
@Data
@NoArgsConstructor
@Entity
public class UserRole {
    @Id
    private Integer id;
    private Integer uid;
    private Integer rid;
}

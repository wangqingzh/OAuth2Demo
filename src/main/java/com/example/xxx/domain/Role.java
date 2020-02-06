package com.example.xxx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 角色表
 */
@Data
@NoArgsConstructor
@Entity
@ToString
@AllArgsConstructor
public class Role {
    @Id
    private Integer id;
    private String name;
    private String nameZh;
}

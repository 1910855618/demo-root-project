package com.demomybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 注解在类上，指定类和数据库表的映射关系
@TableName("user")
public class User {
    // 注解在实体类的某个字段上，表示这个字段对应数据库表的主键
    @TableId("id")
    private Long id;
    // 注解在某个字段上，指定 Java 实体类的字段和数据库表的列的映射关系
    @TableField("name")
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;
}

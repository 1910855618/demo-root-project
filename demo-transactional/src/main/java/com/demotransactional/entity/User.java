package com.demotransactional.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 注解在类上，指定类和数据库表的映射关系
@TableName("t_user")
public class User {
    // @TableId 注解定义字段为表的主键，type 表示主键类型，IdType.AUTO 表示随着数据库 ID 自增
    @TableId(type = IdType.AUTO)
    // 主键 ID
    private Long id;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 性别
    private Integer gender;

    // 职位
    private Integer job;
}

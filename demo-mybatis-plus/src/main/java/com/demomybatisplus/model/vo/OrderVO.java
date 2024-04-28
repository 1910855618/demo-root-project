package com.demomybatisplus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
    // 订单ID
    private Long orderId;
    // 下单用户ID
    private Long userId;
    // 商品名称
    private String goodsName;
    // 商品价格
    private BigDecimal goodsPrice;
    // 用户名
    private String userName;
    // 年龄
    private Integer userAge;
    // 性别
    private Integer userGender;
}

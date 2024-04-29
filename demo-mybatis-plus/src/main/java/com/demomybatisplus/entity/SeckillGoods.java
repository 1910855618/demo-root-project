package com.demomybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_seckill_goods")
public class SeckillGoods {
    // 主键 ID
    @TableId(type = IdType.AUTO)
    private Long id;

    // 商品名称
    private String goodsName;

    // 库存
    private Integer count;

    // 乐观锁版本号
    @Version
    private Integer version;
}

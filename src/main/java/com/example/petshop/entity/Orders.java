package com.example.petshop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petshop.common.base.BaseEntity;
import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("orders")
public class Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单时间
     */
    private LocalDateTime creatTime;

    /**
     * 客户标识符
     */
    private String userId;

    /**
     * 订单总金额
     */
    private BigDecimal total;

    /**
     * 订单发货地址
     */
    private String address;

    /**
     * 订单状态 0---待支付  1--- 已支付
     */
    private Integer state;

    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;

    /**
     * 订单收货人电话
     */
    private String tel;

    /**
     *  订单收货人姓名
     */
    private String name;


}

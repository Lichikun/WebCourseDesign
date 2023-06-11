package com.example.petshop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petshop.common.base.BaseEntity;


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
@TableName("orders_item")
public class OrdersItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单标识符
     */
    private String ordersId;

    /**
     * 商品标识符
     */
    private String goodsId;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品数量
     */
    private Integer goodsQuantity;


}

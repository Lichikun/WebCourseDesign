package com.example.petshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petshop.common.base.BaseEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("shopping_cart")
public class ShoppingCart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户标识符
     */
    private String userId;

    /**
     * 商品标识符
     */
    private String goodsId;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 是否禁用 0---选择   1-未选中
     */
    private Integer opt;

    private Integer type;
}

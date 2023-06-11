package com.example.petshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petshop.common.base.BaseEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 宠物表
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pets")
public class Pets extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 宠物名字
     */
    private String name;

    /**
     * 宠物品种
     */
    private String type;

    /**
     * 宠物价格
     */
    private Double price;

    /**
     * 所属商店
     */
    private String shopId;

    /**
     * 宠物性别
     */
    private String sex;

    /**
     * 宠物年龄
     */
    private Integer age;


    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;

    /**
     * 宠物描述
     */
    private String desc;

    /**
     * 0---没卖 1---卖了
     */
    private Integer stock;
}

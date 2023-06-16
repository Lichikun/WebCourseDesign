package com.example.petshop.entity;

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
@TableName("shop")
public class Shop extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商店名字
     */
    private String name;

    /**
     * 商店地址
     */
    private String address;

    /**
     * 商店电话
     */
    private String tele;

    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;

    /**
     * 商店介绍
     */
    private String description;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    private String picture;


}

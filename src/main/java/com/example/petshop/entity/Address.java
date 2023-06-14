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
@TableName("address")
public class Address extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人联系电话
     */
    private String tele;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 是否默认地址0--是 1--- 否
     */
    private Integer isDefault;

    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;

    /**
     * 省-市-区
     */
    private String label;

    /**
     * 三级数组之序列1
     */
    private String arr1;

    /**
     * 三级数组之序列2
     */
    private String arr2;

    /**
     * 三级数组之序列3
     */
    private String arr3;

    /**
     * 城市编号
     */
    private String cityCode;


}

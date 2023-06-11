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
@TableName("picture")
public class Picture extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属id（商店或宠物或商品）
     */
    private String belongId;

    /**
     * 图片地址
     */
    private String url;


}

package com.example.petshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petshop.common.base.BaseEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收藏表，用于会员收藏宠物
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("collect")
public class Collect extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏者标识符
     */
    private String userId;

    /**
     * 宠物标识符
     */
    private String petsId;


}

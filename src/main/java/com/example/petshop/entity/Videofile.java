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
@TableName("videofile")
public class Videofile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 视频地址
     */
    private String url;

    /**
     * 关联物品
     */
    private String belongId;

    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;


    /**
     *  0---宠物   1-商品
     */
    private Integer state;

}

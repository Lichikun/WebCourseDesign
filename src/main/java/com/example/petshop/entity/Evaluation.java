package com.example.petshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petshop.common.base.BaseEntity;
import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评价表
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("evaluation")
public class Evaluation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价的时间
     */
    private String time;

    /**
     * 评价的分数
     */
    private Integer score;

    /**
     * 评价用户的ID
     */
    private String userId;

    /**
     * 被评价商户ID
     */
    private String shopId;

    /**
     * 购买物品ID
     */
    private String goodsId;

    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;

    private String orderId;

}

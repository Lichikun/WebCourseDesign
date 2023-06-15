package com.example.petshop.entity;

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
@TableName("dictype")
public class Dictype extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典类型名称
     */
    private String name;

    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;

    /**
     * 字典类型值
     */
    private String value;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private String fromPage;

}

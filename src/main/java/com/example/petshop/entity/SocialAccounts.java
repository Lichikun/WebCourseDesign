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
@TableName("social_accounts")
public class SocialAccounts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关联user表中id
     */
    private String userId;

    /**
     * 第三方登录提供者
     */
    private String provider;

    /**
     * 第三方登录提供者分配的用户id
     */
    private String providerUserId;

    /**
     * 访问第三方API的访问令牌
     */
    private String accessToken;

    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;


}

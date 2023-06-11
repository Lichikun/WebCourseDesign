package com.example.petshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petshop.common.base.BaseEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户登入名称(登入账号)
     */
    private String username;

    /**
     * 用户登入密码(加密后)
     */
    private String password;

    /**
     * 用户密码加密盐
     */
    private String salt;

    /**
     * 用户联系电话
     */
    private String tele;

    /**
     * 用户权限 0--普通会员   1--高级会员   2--后台管理员
     */
    private Integer state;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户指纹
     */
    private String fingerPrint;

    /**
     * 用户人脸
     */
    private String facial;

    /**
     * 用户头像URL
     */
    private String avatar;

    /**
     * 用户默认选择地址
     */
    private String defaultAddress;

    /**
     * 用户性别 0---male  1-female
     */
    private Integer sex;

    /**
     * 是否禁用 0---启动   1-禁用
     */
    private Integer useful;


}

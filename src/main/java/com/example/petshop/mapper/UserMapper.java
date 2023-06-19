package com.example.petshop.mapper;

import com.example.petshop.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
public interface UserMapper extends BaseMapper<User> {
    @Delete("DELETE user,address,collect,shopping_cart\n" +
            "FROM user\n" +
            "LEFT JOIN address ON user.id = address.user_id\n" +
            "LEFT JOIN shopping_cart ON user.id = shopping_cart.user_id\n" +
            "LEFT JOIN collect ON user.id = collect.user_id\n" +
            "WHERE user.id = #{id}")
    Boolean userMultiDelete(@Param("id") String id);
}

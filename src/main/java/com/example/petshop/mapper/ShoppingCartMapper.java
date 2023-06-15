package com.example.petshop.mapper;

import com.example.petshop.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petshop.vo.shoppingCartVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    @Select("SELECT sc.id AS id, \n" +
            "       g.id AS goods_id, \n" +
            "       g.name AS goods_name, \n" +
            "       g.price AS goods_price, \n" +
            "       s.id AS shop_id, \n" +
            "       s.name AS shop_name, \n" +
            "       sc.goods_num, \n" +
            "       sc.opt, \n" +
            "       p.id AS picture_id, \n" +
            "       p.url AS picture_url, \n" +
            "       p.state \n" +
            "FROM shopping_cart sc \n" +
            "JOIN goods g ON sc.goods_id = g.id \n" +
            "JOIN shop s ON g.shop_id = s.id \n" +
            "LEFT JOIN picture p ON g.id = p.belong_id AND p.state = '0'\n" +
            "WHERE sc.id = '${id}'  ")
    List<shoppingCartVo> getByCartId(String id);
}

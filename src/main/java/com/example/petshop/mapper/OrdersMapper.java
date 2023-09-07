package com.example.petshop.mapper;

import com.example.petshop.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petshop.vo.goodsVo;
import com.example.petshop.vo.ordersVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    @Select(
            "SELECT o.id AS order_id, o.creat_time, o.user_id, o.total, o.address, o.state, o.useful,o.`name` ,\n" +
                    "       oi.id AS item_id, oi.goods_quantity, oi.goods_price,\n" +
                    "       g.id AS goods_id, g.`name` AS goods_name,g.category as goods_category\n" +
                    "FROM orders o\n" +
                    "JOIN orders_item oi ON o.id = oi.orders_id\n" +
                    "JOIN goods g ON oi.goods_id = g.id\n" +
                    "WHERE o.id = #{ordersId};")
    List<ordersVo> getOneOrders(@Param("ordersId") String id);

    @Select(
            "SELECT o.id AS order_id, o.creat_time, o.user_id, o.total, o.address, o.state, o.useful,o.`name` ,\n" +
                    "       oi.id AS item_id, oi.goods_quantity, oi.goods_price,\n" +
                    "       g.id AS goods_id, g.`name` AS goods_name ,g.category as goods_category\n" +
                    "FROM orders o\n" +
                    "JOIN orders_item oi ON o.id = oi.orders_id\n" +
                    "JOIN goods g ON oi.goods_id = g.id\n" +
                    "LIMIT #{pageSize} OFFSET #{pageNum};")
    List<ordersVo> getAllOrders(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}

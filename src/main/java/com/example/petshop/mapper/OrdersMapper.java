package com.example.petshop.mapper;

import com.example.petshop.entity.Goods;
import com.example.petshop.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.petshop.entity.OrdersItem;
import com.example.petshop.entity.Pets;
import com.example.petshop.vo.userOrderVo;

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

    @Select("SELECT id FROM user WHERE name=#{userName}")
    String getUserId(@Param("userName") String userName);



    @Select("SELECT t1.*,\n" +
            "t2.orders_id, t2.goods_id,t2.goods_price,t2.goods_quantity,\n" +
            "t3.id id_goods,t3.name goods_or_pets_name, \n" +
            "t4.belong_id,t4.url\n" +
            "FROM orders AS t1\n" +
            "JOIN orders_item AS t2 ON t1.id = t2.orders_id\n" +
            "JOIN goods AS t3 ON t2.goods_id = t3.id\n" +
            "JOIN picture AS t4 ON t3.id = t4.belong_id\n" +
            "WHERE t1.state = #{state} AND user_id=#{user_id}AND t4.state=0;")
    List<userOrderVo> getGoods(@Param("state") Integer state,@Param("user_id") String user_id);

    @Select("SELECT t1.*,\n" +
            "t2.orders_id, t2.goods_id,t2.goods_price,t2.goods_quantity,\n" +
            "t3.id id_goods,t3.name goods_or_pets_name, \n" +
            "t4.belong_id,t4.url\n" +
            "FROM orders AS t1\n" +
            "JOIN orders_item AS t2 ON t1.id = t2.orders_id\n" +
            "JOIN goods AS t3 ON t2.goods_id = t3.id\n" +
            "JOIN picture AS t4 ON t3.id = t4.belong_id\n" +
            "WHERE user_id=#{user_id}AND t4.state=0;")
    List<userOrderVo> getALLGoods(@Param("user_id") String user_id);

    @Select("SELECT t1.*,\n" +
            "t2.orders_id, t2.goods_id,t2.goods_price,t2.goods_quantity,\n" +
            "t3.id id_goods,t3.name goods_or_pets_name, \n" +
            "t4.belong_id,t4.url\n" +
            "FROM orders AS t1\n" +
            "JOIN orders_item AS t2 ON t1.id = t2.orders_id\n" +
            "JOIN pets AS t3 ON t2.goods_id = t3.id\n" +
            "JOIN picture AS t4 ON t3.id = t4.belong_id\n" +
            "WHERE t1.state = #{state} AND user_id=#{user_id} AND t4.state=0;")
    List<userOrderVo> getPets(@Param("state") Integer state,@Param("user_id") String user_id);

    @Select("SELECT t1.*,\n" +
            "t2.orders_id, t2.goods_id,t2.goods_price,t2.goods_quantity,\n" +
            "t3.id id_goods,t3.name goods_or_pets_name, \n" +
            "t4.belong_id,t4.url\n" +
            "FROM orders AS t1\n" +
            "JOIN orders_item AS t2 ON t1.id = t2.orders_id\n" +
            "JOIN pets AS t3 ON t2.goods_id = t3.id\n" +
            "JOIN picture AS t4 ON t3.id = t4.belong_id\n" +
            "WHERE user_id=#{user_id} AND t4.state=0;")
    List<userOrderVo> getAllPets(@Param("user_id") String user_id);

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

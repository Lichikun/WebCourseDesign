package com.example.petshop.mapper;

import com.example.petshop.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petshop.vo.goodsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 宠物周边商品表 Mapper 接口
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    @Select("SELECT *FROM goods JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=goods.id WHERE p.state=0 ORDER BY goods.purchase_quantity DESC LIMIT #{pageNum}, #{pageSize}")
    List<goodsVo> pageByPurchase(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("SELECT *FROM goods JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=goods.id WHERE goods.id=#{id} ")
    List<goodsVo> getById(@Param("id") String id);

    @Select("SELECT * FROM goods JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE name LIKE '%${name}%' AND p.state = 0  LIMIT #{pageNum}, #{pageSize}")
    List<goodsVo> search(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("name") String name);

    @Select("SELECT *FROM goods JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=goods.id WHERE p.state=0 ORDER BY goods.${orderByDsc} ${orderAsc} LIMIT #{pageNum}, #{pageSize}")
    List<goodsVo>getHomePageGoods(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("orderByDsc") String orderByDec,@Param("orderAsc") String orderAsc);
    @Select("SELECT *FROM goods JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=goods.id WHERE p.state=0 AND category=#{category} ORDER BY goods.${orderByDsc} ${orderAsc} LIMIT #{pageNum}, #{pageSize}")
    List<goodsVo>getCategoryPageGoods(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("category") String type,@Param("orderByDsc") String orderByDec,@Param("orderAsc") String orderAsc);

    @Select("SELECT * FROM goods JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE name LIKE '%${name}%' AND p.state = 0 ORDER BY goods.purchase_quantity DESC LIMIT #{pageNum}, #{pageSize}")
    List<goodsVo> downQuantitySearch(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("name") String name);

    @Select("SELECT * FROM goods JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE name LIKE '%${name}%' AND p.state = 0 ORDER BY goods.purchase_quantity ASC LIMIT #{pageNum}, #{pageSize}")
    List<goodsVo> upQuantitySearch(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("name") String name);

    @Select("SELECT * FROM goods JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE name LIKE '%${name}%' AND p.state = 0 ORDER BY goods.price DESC LIMIT #{pageNum}, #{pageSize}")
    List<goodsVo> downPriceSearch(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("name") String name);

    @Select("SELECT * FROM goods JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE name LIKE '%${name}%' AND p.state = 0 ORDER BY goods.price ASC LIMIT #{pageNum}, #{pageSize}")
    List<goodsVo> upPriceSearch(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("name") String name);

    @Select("SELECT *FROM goods JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=goods.id WHERE goods.shop_id=#{shop_id} AND p.state = 0 ")
    List<goodsVo> getByShopid(@Param("shop_id") String shop_id);
}

package com.example.petshop.mapper;

import com.example.petshop.entity.Evaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petshop.vo.commentVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 评价表 Mapper 接口
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
public interface EvaluationMapper extends BaseMapper<Evaluation> {
    @Select("SELECT id FROM user WHERE name=#{userName}")
    String getUserId(@Param("userName") String userName);
    @Select("SELECT shop_id FROM goods WHERE id=#{goodsId}")
    String getGooodsShopId(@Param("goodsId") String goodsId);
    @Select("SELECT * FROM evaluation WHERE goods_id=#{goodsId} AND user_id=#{userId} AND order_id=#{orderId}")
    Evaluation getComment(@Param("goodsId") String goodsId,@Param("userId") String userId,@Param("orderId") String orderId);
    @Select("SELECT *\n" +
            "FROM evaluation A\n" +
            "LEFT JOIN user B\n" +
            "ON A.user_id = B.id \n" +
            "WHERE goods_id=#{goodsId}")
    List<commentVo> getGooodsComment(@Param("goodsId") String goodsId);
}

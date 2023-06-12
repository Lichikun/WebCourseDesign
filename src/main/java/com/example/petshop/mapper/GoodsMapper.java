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
}

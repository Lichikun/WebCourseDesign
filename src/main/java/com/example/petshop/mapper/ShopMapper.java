package com.example.petshop.mapper;

import com.example.petshop.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.petshop.vo.shopVo;
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
public interface ShopMapper extends BaseMapper<Shop> {
    @Select("SELECT * FROM shop  WHERE name LIKE '%${name}%' LIMIT #{pageNum}, #{pageSize}")
    List<shopVo> search(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name);
}

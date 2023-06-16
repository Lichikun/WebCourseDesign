package com.example.petshop.mapper;

import com.example.petshop.entity.Pets;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petshop.vo.goodsVo;
import com.example.petshop.vo.petsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 宠物表 Mapper 接口
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
public interface PetsMapper extends BaseMapper<Pets> {
    @Select("SELECT *FROM pets JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=pets.id WHERE pets.id=#{id} ")
    List<petsVo> getById(@Param("id") String id);//得到宠物详细信息

    @Select("SELECT * FROM pets JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE name LIKE '%${name}%' AND p.state = 0")
    List<petsVo> search(@Param("name") String name);
}

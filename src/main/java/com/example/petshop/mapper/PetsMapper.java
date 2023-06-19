package com.example.petshop.mapper;

import com.example.petshop.entity.Pets;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petshop.vo.goodsVo;
import com.example.petshop.vo.petsVo;
import org.apache.ibatis.annotations.Delete;
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

    @Select("SELECT * FROM pets JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE type LIKE '%${type}%' AND p.state = 0  LIMIT #{pageNum}, #{pageSize}")
    List<petsVo> search(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("type") String type);
    @Select("SELECT *FROM pets JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=pets.id WHERE p.state=0 ORDER BY pets.${orderByDsc} ${orderAsc} LIMIT #{pageNum}, #{pageSize}")
    List<petsVo>getHomePagePets(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("orderByDsc") String orderByDec,@Param("orderAsc") String orderAsc);
    @Select("SELECT *FROM pets JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=pets.id WHERE p.state=0 AND type=#{category} ORDER BY pets.${orderByDsc} ${orderAsc} LIMIT #{pageNum}, #{pageSize}")
    List<petsVo>getCategoryPagePets(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("category") String type,@Param("orderByDsc") String orderByDec,@Param("orderAsc") String orderAsc);
    @Select("SELECT * FROM pets JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE type LIKE '%${type}%' AND p.state = 0 ORDER BY pets.price DESC LIMIT #{pageNum}, #{pageSize}")
    List<petsVo> downPriceSearch(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("type") String type);

    @Select("SELECT * FROM pets JOIN (SELECT url, belong_id, state FROM picture) AS p ON p.belong_id = id WHERE type LIKE '%${type}%' AND p.state = 0 ORDER BY pets.price ASC LIMIT #{pageNum}, #{pageSize}")
    List<petsVo> upPriceSearch(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("type") String type);

    @Select("SELECT *FROM pets JOIN (SELECT url,belong_id,state FROM picture) AS p on p.belong_id=pets.id WHERE pets.shop_id=#{shop_id} AND p.state = 0 ")
    List<petsVo> getByShopid(@Param("shop_id") String shop_id);

    @Delete("DELETE pets, picture\n" +
            "FROM pets\n" +
            "LEFT JOIN picture ON pets.id = picture.belong_id\n" +
            "WHERE pets.id = #{id};")
    Boolean petsMultiDelete(@Param("id") String id);
}

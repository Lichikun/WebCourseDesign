package com.example.petshop.mapper;

import com.example.petshop.entity.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petshop.vo.collectVo;
import com.example.petshop.vo.collectVoGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 收藏表，用于会员收藏宠物 Mapper 接口
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
public interface CollectMapper extends BaseMapper<Collect> {
    @Select("SELECT c.user_id,c.user_name,c.pets_id, p.*, i.belong_id,i.url,i.state FROM collect c JOIN pets p ON c.pets_id = p.id JOIN picture i ON p.id = i.belong_id WHERE c.user_name = #{userName} AND i.state=0 LIMIT #{pageNum},#{pageSize};  ")
    List<collectVo> pageByuserName(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("userName") String userName);
    @Select("SELECT c.user_id,c.user_name,c.pets_id, g.*, i.belong_id,i.url,i.state FROM collect c JOIN goods g ON c.pets_id = g.id JOIN picture i ON g.id = i.belong_id WHERE c.user_name =#{userName} AND i.state=0 LIMIT #{pageNum},#{pageSize};  ")
    List<collectVoGoods> pageToGetGoods(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("userName") String userName);
    @Select("SELECT id FROM user WHERE name=#{userName}")
    String getUserId(@Param("userName") String userName);
}

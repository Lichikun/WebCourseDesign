package com.example.petshop.mapper;

import com.example.petshop.entity.Videofile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petshop.vo.goodsVo;
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
public interface VideofileMapper extends BaseMapper<Videofile> {
    @Select("SELECT *FROM videofile")
    List<Videofile> list();

}

package com.example.petshop.mapper;

import com.example.petshop.entity.Dic;
import com.example.petshop.entity.Dictype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface DictypeMapper extends BaseMapper<Dictype> {
    @Select("SELECT * FROM dic WHERE dictype_id=#{id}")
    List<Dic>getDicbyid(@Param("id")String id);
}

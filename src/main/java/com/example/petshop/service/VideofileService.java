package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Videofile;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface VideofileService extends IService<Videofile> {

    Boolean add(Videofile videofile);
    void deleteByIds(String ids);
    Boolean update(Videofile videofile);
    Videofile getByValue(String value,String name);
    List<Videofile> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<Videofile> page(Integer pageNum, Integer pageSize, String name);
}
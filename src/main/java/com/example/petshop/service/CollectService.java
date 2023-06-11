package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Collect;

import java.util.List;

/**
* <p>
    * 收藏表，用于会员收藏宠物 服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface CollectService extends IService<Collect> {

    Boolean add(Collect collect);
    void deleteByIds(String ids);
    Boolean update(Collect collect);
    Collect getByValue(String value,String name);
    List<Collect> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<Collect> page(Integer pageNum, Integer pageSize, String name);
}
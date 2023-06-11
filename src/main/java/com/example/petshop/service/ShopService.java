package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Shop;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface ShopService extends IService<Shop> {

    Boolean add(Shop shop);
    void deleteByIds(String ids);
    Boolean update(Shop shop);
    Shop getByValue(String value,String name);
    List<Shop> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<Shop> page(Integer pageNum, Integer pageSize, String name);
}
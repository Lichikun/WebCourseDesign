package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.ShoppingCart;

import java.util.List;

/**
* <p>
    * 购物车表 服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface ShoppingCartService extends IService<ShoppingCart> {

    Boolean add(ShoppingCart shoppingCart);
    void deleteByIds(String ids);
    Boolean update(ShoppingCart shoppingCart);
    ShoppingCart getByValue(String value,String name);
    List<ShoppingCart> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<ShoppingCart> page(Integer pageNum, Integer pageSize, String name);
}
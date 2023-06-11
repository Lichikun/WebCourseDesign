package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.OrdersItem;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface OrdersItemService extends IService<OrdersItem> {

    Boolean add(OrdersItem ordersItem);
    void deleteByIds(String ids);
    Boolean update(OrdersItem ordersItem);
    OrdersItem getByValue(String value,String name);
    List<OrdersItem> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<OrdersItem> page(Integer pageNum, Integer pageSize, String name);
}
package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Orders;

import com.example.petshop.vo.userOrderVo;

import com.example.petshop.vo.ordersVo;


import java.util.List;
import java.util.Map;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface OrdersService extends IService<Orders> {

    Orders add(Orders orders);
    void deleteByIds(String ids);
    Boolean update(Orders orders);
    Orders getByValue(String value,String name);
    List<Orders> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Integer flag);
    Boolean updateState(String id, Integer flag);
    Page<Orders> page(Integer pageNum, Integer pageSize, String value,String name);

    List<userOrderVo> getUserOrderByState(Integer state);


    List<ordersVo> getByOrdersId(String id);

    List<ordersVo> getOrders(Integer pageNum, Integer pageSize);
    Boolean setUserOrserState(String id,Integer state);
    Boolean setUserOrserContent(String id,String reason);
}
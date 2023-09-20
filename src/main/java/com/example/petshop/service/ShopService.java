package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Shop;
import com.example.petshop.vo.goodsVo;
import com.example.petshop.vo.shopVo;

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

    List<Shop> shopListByValue(String value, String name);

    List<Shop> listByValue(double  distance, String longitude, String latitude);
    Boolean updateUsefulByIds(String id, Integer flag);
    Page<Shop> page(Integer pageNum, Integer pageSize, String value, String name);
    List<shopVo> search(Integer pageNum, Integer pageSize, String name);
    Shop getById(String id);
    Integer getShopNum();
}
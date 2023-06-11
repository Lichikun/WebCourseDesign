package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Goods;

import java.util.List;

/**
* <p>
    * 宠物周边商品表 服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface GoodsService extends IService<Goods> {

    Boolean add(Goods goods);
    void deleteByIds(String ids);
    Boolean update(Goods goods);
    Goods getByValue(String value,String name);
    List<Goods> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<Goods> page(Integer pageNum, Integer pageSize, String name);
}
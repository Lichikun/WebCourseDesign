package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Pets;
import com.example.petshop.vo.goodsVo;
import com.example.petshop.vo.petsVo;

import java.util.List;

/**
* <p>
    * 宠物表 服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface PetsService extends IService<Pets> {

    Boolean add(Pets pets);
    void deleteByIds(String ids);
    Boolean update(Pets pets);
    Pets getByValue(String value,String name);
    List<Pets> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Integer flag);
    Page<Pets> page(Integer pageNum, Integer pageSize,String value, String name);
    List<petsVo>getResultById(String id);
    List<petsVo> pageByPets(String pageFrom, String type, Integer pageNum, Integer pageSize, String orderByDsc, String orderAsc);
    List<petsVo> search(Integer pageNum, Integer pageSize,String type);
    List<petsVo> downPriceSearch(Integer pageNum, Integer pageSize,String type);
    List<petsVo> upPriceSearch(Integer pageNum, Integer pageSize,String type);
    List<petsVo> getByShopid(String shop_id);
}
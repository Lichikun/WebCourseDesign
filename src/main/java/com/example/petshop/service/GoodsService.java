package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petshop.entity.Evaluation;
import com.example.petshop.entity.Goods;
import com.example.petshop.entity.Pets;
import com.example.petshop.vo.goodsVo;

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

    List<Goods> listByValue(String value, String name);

    List<goodsVo> getById(String id);
    Boolean updateUsefulByIds(String id, Integer flag);

    Page<Goods> pageByValue(Integer pageNum, Integer pageSize, String value, String name);

    List<goodsVo> page(Integer pageNum, Integer pageSize);

    List<goodsVo>pageByGoods(String pageFrom,String type,Integer pageNum,Integer pageSize,String orderByDsc,String orderAsc);
    List<goodsVo> search(Integer pageNum, Integer pageSize,String name);
    List<goodsVo> downQuantitySearch(Integer pageNum, Integer pageSize,String name);
    List<goodsVo> upQuantitySearch(Integer pageNum, Integer pageSize,String name);
    List<goodsVo> downPriceSearch(Integer pageNum, Integer pageSize,String name);
    List<goodsVo> upPriceSearch(Integer pageNum, Integer pageSize,String name);
    List<goodsVo> getByShopid(String shop_id);
}

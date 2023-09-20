package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.entity.Pets;
import com.example.petshop.mapper.GoodsMapper;
import com.example.petshop.entity.Goods;
import com.example.petshop.service.GoodsService;
import com.example.petshop.vo.goodsVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
    * 宠物周边商品表 服务层实现类
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,Goods> implements GoodsService {


    @Override
    public Boolean add(Goods goods) {
        goods.setUseful(1);
        goods.setPurchaseQuantity(0);
        this.save(goods);
        return true;
    }

    @Override
    public Boolean update(Goods goods) {
        this.updateById(goods);
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }

    @Override
    public Boolean updateUsefulByIds(String ids, Integer flag) {
        //ids  若干个id 用逗号隔开
        String[] aryIds = ids.split(",");
        for(String id: aryIds){

            //查找符合的数据
            UpdateWrapper<Goods> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Goods goods = this.getOne(UpdateWrapper);
            goods.setUseful(flag);
            //执行
            this.update(goods);
        }
        return true;
    }

    @Override
    public Goods getByValue(String value,String name){
    QueryWrapper<Goods> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }
    @Override
    public List<Goods> listByValue(String value, String name){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(value,name);

        return this.list(queryWrapper);
    }

    @Override
    public List<goodsVo> getById (String id){

            return this.baseMapper.getById(id);
    }

    @Override
    public Page<Goods> pageByValue(Integer pageNum, Integer pageSize, String value, String name) {
        Page<Goods> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(value,name);

        return this.page(page,queryWrapper);
    }

    @Override
    public List<goodsVo> homePage(Integer pageNum, Integer pageSize) {

        return this.baseMapper.pageByPurchase((pageNum-1)*pageSize,pageSize);
    }


    @Override
    public List<goodsVo> search(Integer pageNum, Integer pageSize,String name) {
        List<goodsVo> b=baseMapper.search((pageNum-1)*pageSize,pageSize,name);
        return b;
    }

    @Override
    public List<goodsVo> pageByGoods(String pageFrom, String type, Integer pageNum, Integer pageSize, String orderByDsc, String orderAsc) {
        if(pageFrom.equals("home")){
            return this.baseMapper.getHomePageGoods((pageNum-1)*pageSize,pageSize,orderByDsc,orderAsc);
        }else {
            return this.baseMapper.getCategoryPageGoods((pageNum-1)*pageSize,pageSize,type,orderByDsc,orderAsc);
        }


    }
  
    @Override
    public List<goodsVo> downQuantitySearch(Integer pageNum, Integer pageSize,String name) {
        List<goodsVo> b=baseMapper.downQuantitySearch((pageNum-1)*pageSize,pageSize,name);
        return b;
    }

    @Override
    public List<goodsVo> upQuantitySearch(Integer pageNum, Integer pageSize,String name) {
        List<goodsVo> b=baseMapper.upQuantitySearch((pageNum-1)*pageSize,pageSize,name);
        return b;
    }

    @Override
    public List<goodsVo> downPriceSearch(Integer pageNum, Integer pageSize,String name) {
        List<goodsVo> b=baseMapper.downPriceSearch((pageNum-1)*pageSize,pageSize,name);
        return b;
    }

    @Override
    public List<goodsVo> upPriceSearch(Integer pageNum, Integer pageSize,String name) {
        List<goodsVo> b=baseMapper.upPriceSearch((pageNum-1)*pageSize,pageSize,name);
        return b;
    }

    @Override
    public List<goodsVo> getByShopid(String shop_id) {
        List<goodsVo> b=baseMapper.getByShopid(shop_id);
        return b;
    }


    @Override
    public List<goodsVo> getByIds(String id) {
        List<goodsVo> b=baseMapper.getByIds(id);
        return b;
    }

    @Override
    public List<goodsVo> getGoodsFile(Integer pageNum, Integer pageSize) {
        List<goodsVo> list = baseMapper.getGoodFile(pageNum,pageSize);
        return list;
    }

    @Override
    public List<goodsVo> getGoodsVideoFile(Integer pageNum, Integer pageSize) {
        List<goodsVo> list = baseMapper.getGoodVedioFile(pageNum,pageSize);
        return list;
    }

    @Override
    public boolean checkAvailability(String id,Integer num,String type){
        QueryWrapper<Goods> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("id",id);
        Goods goods = this.getOne(QueryWrapper);
        Integer stock = goods.getStock() - num;
        if(stock >= 0){
            if(type == "pay"){
                goods.setStock(stock);
                this.update(goods);
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Integer getGoodsNum() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        return this.count(queryWrapper);
    }

    @Override
    public Map<String, Integer> getGoodsPricePhase() {
        Map<String, Integer> dataItemCountByPriceRange = new HashMap<>();

        QueryWrapper<Goods> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.between("price", 1.0, 100.0);
        int range1Count = this.count(queryWrapper1);
        dataItemCountByPriceRange.put("1-100", range1Count);

        QueryWrapper<Goods> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.between("price", 100.0, 300.0);
        int range2Count = this.count(queryWrapper2);
        dataItemCountByPriceRange.put("100-300", range2Count);

        QueryWrapper<Goods> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.between("price", 300.0, 500.0);
        int range3Count = this.count(queryWrapper3);
        dataItemCountByPriceRange.put("300-500", range3Count);

        QueryWrapper<Goods> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.gt("price", 500.0);
        int range4Count = this.count(queryWrapper4);
        dataItemCountByPriceRange.put("500+", range4Count);

        return dataItemCountByPriceRange;
    }

    @Override
    public Map<String, Integer> getGoodsTypeNum() {
        Map<String, Integer> itemCountByType = new HashMap<>();

        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("category")
                .groupBy("category");
        List<Map<String, Object>> resultList = this.listMaps(queryWrapper);
        for (Map<String, Object> result : resultList) {
            String category = (String) result.get("category");
            int count = this.count(new QueryWrapper<Goods>().eq("category", category));
            itemCountByType.put(category, count);
        }

        return itemCountByType;
    }
}
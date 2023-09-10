package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.mapper.GoodsMapper;
import com.example.petshop.entity.Goods;
import com.example.petshop.service.GoodsService;
import com.example.petshop.vo.goodsVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
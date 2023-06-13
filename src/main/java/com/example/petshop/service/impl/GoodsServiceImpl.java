package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    public Boolean updateUsefulByIds(String ids, Boolean flag) {
        //ids  若干个id 用逗号隔开
        String[] aryIds = ids.split(",");
        for(String id: aryIds){

            //查找符合的数据
            UpdateWrapper<Goods> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Goods goods = this.getOne(UpdateWrapper);

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
    public List<goodsVo> getById (String id){

            return this.baseMapper.getById(id);
    }

    @Override
    public List<goodsVo> page(Integer pageNum, Integer pageSize) {

        return this.baseMapper.pageByPurchase((pageNum-1)*pageSize,pageSize);
    }

}
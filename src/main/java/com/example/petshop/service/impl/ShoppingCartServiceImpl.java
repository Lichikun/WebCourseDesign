package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.ShoppingCartMapper;
import com.example.petshop.entity.ShoppingCart;
import com.example.petshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
    * 购物车表 服务层实现类
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper,ShoppingCart> implements ShoppingCartService {


    @Override
    public Boolean add(ShoppingCart shoppingCart) {
        this.save(shoppingCart);
        return true;
    }

    @Override
    public Boolean update(ShoppingCart shoppingCart) {
        this.updateById(shoppingCart);
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
            UpdateWrapper<ShoppingCart> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            ShoppingCart shoppingCart = this.getOne(UpdateWrapper);

            //执行
            this.update(shoppingCart);
        }
        return true;
    }

    @Override
    public ShoppingCart getByValue(String value,String name){
    QueryWrapper<ShoppingCart> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<ShoppingCart> listByValue (String value,String name){
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<ShoppingCart> page(Integer pageNum,Integer pageSize,String name) {
        Page<ShoppingCart> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

}
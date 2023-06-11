package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.ShopMapper;
import com.example.petshop.entity.Shop;
import com.example.petshop.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
    *  服务层实现类
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper,Shop> implements ShopService {


    @Override
    public Boolean add(Shop shop) {
        this.save(shop);
        return true;
    }

    @Override
    public Boolean update(Shop shop) {
        this.updateById(shop);
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
            UpdateWrapper<Shop> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Shop shop = this.getOne(UpdateWrapper);

            //执行
            this.update(shop);
        }
        return true;
    }

    @Override
    public Shop getByValue(String value,String name){
    QueryWrapper<Shop> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Shop> listByValue (String value,String name){
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Shop> page(Integer pageNum,Integer pageSize,String name) {
        Page<Shop> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

}
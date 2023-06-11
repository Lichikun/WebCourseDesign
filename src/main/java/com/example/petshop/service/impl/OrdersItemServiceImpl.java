package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.OrdersItemMapper;
import com.example.petshop.entity.OrdersItem;
import com.example.petshop.service.OrdersItemService;
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
public class OrdersItemServiceImpl extends ServiceImpl<OrdersItemMapper,OrdersItem> implements OrdersItemService {


    @Override
    public Boolean add(OrdersItem ordersItem) {
        this.save(ordersItem);
        return true;
    }

    @Override
    public Boolean update(OrdersItem ordersItem) {
        this.updateById(ordersItem);
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
            UpdateWrapper<OrdersItem> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            OrdersItem ordersItem = this.getOne(UpdateWrapper);

            //执行
            this.update(ordersItem);
        }
        return true;
    }

    @Override
    public OrdersItem getByValue(String value,String name){
    QueryWrapper<OrdersItem> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<OrdersItem> listByValue (String value,String name){
        QueryWrapper<OrdersItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<OrdersItem> page(Integer pageNum,Integer pageSize,String name) {
        Page<OrdersItem> page = new Page<>(pageNum,pageSize);
        QueryWrapper<OrdersItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

}
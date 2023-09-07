package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.OrdersMapper;
import com.example.petshop.entity.Orders;
import com.example.petshop.service.OrdersService;
import com.example.petshop.vo.ordersVo;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper,Orders> implements OrdersService {


    @Override
    public Orders add(Orders orders) {
        orders.setCreatTime(DateTool.getCurrTime());
        orders.setUseful(1);
        this.save(orders);
        return orders;
    }

    @Override
    public Boolean update(Orders orders) {
        this.updateById(orders);
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
            UpdateWrapper<Orders> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Orders orders = this.getOne(UpdateWrapper);
            orders.setUseful(flag);
            //执行
            this.update(orders);
        }
        return true;
    }

    @Override
    public Boolean updateState(String ids, Integer flag) {
        //ids  若干个id 用逗号隔开
        String[] aryIds = ids.split(",");
        for(String id: aryIds){

            //查找符合的数据
            UpdateWrapper<Orders> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Orders orders = this.getOne(UpdateWrapper);
            orders.setState(flag);
            //执行
            this.update(orders);
        }
        return true;
    }

    @Override
    public Orders getByValue(String value,String name){
    QueryWrapper<Orders> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Orders> listByValue (String value,String name){
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Orders> page(Integer pageNum,Integer pageSize,String value,String name) {
        Page<Orders> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(value,name);

        return this.page(page,queryWrapper);
    }
    @Override
    public ordersVo getByOrdersId(String id){
        List<ordersVo> ordersVos = baseMapper.getOneOrders(id);
        return ordersVos.get(0);
    }

    @Override
    public List<ordersVo> getOrders(){
        List<ordersVo> ordersVos = baseMapper.getAllOrders();
        return ordersVos;
    }
}
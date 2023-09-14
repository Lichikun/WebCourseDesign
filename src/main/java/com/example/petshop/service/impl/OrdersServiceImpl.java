package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.common.utils.JwtTokenProvider;
import com.example.petshop.entity.Goods;
import com.example.petshop.entity.OrdersItem;
import com.example.petshop.entity.Pets;
import com.example.petshop.mapper.OrdersMapper;
import com.example.petshop.entity.Orders;
import com.example.petshop.service.OrdersService;

import com.example.petshop.vo.userOrderVo;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.petshop.vo.ordersVo;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private HttpServletRequest request;
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
    public List<ordersVo> getByOrdersId(String id){
        List<ordersVo> ordersVos = baseMapper.getOneOrders(id);
        return ordersVos;
    }

    @Override

    public List<userOrderVo> getUserOrderByState(Integer state) {
        String token = request.getHeader("Authorization");
        JwtTokenProvider jwtTokenProvider=new JwtTokenProvider();
        String userName=jwtTokenProvider.getUsernameFromToken(token);
        String userId=this.baseMapper.getUserId(userName);
        List<userOrderVo>list;
        if(state>=0) {
            list = this.baseMapper.getGoods(state, userId);
            list.addAll(this.baseMapper.getPets(state, userId));
        }else{
            list = this.baseMapper.getALLGoods(userId);
            list.addAll(this.baseMapper.getAllPets(userId));
        }
        Map<String,List<userOrderVo>>  userMap =  new HashMap<>();
        list.forEach(item->{
            List<userOrderVo> users = userMap.get(item.getOrdersId());
            if(users==null){
                users = new ArrayList<>();
                users.add(item);
                userMap.put(item.getOrdersId(),users);
            }else{
                users.add(item);
            }
        });
        List<userOrderVo>list1=new ArrayList<>();
        Set<String> keySet = userMap.keySet();
        for (String key: keySet){
            List<userOrderVo> value = userMap.get(key);
            if(!value.isEmpty()) {
                userOrderVo userOrderVo = new userOrderVo();
                userOrderVo.setState(value.get(0).getState());
                userOrderVo.setGoodsQuantity(value.get(0).getGoodsQuantity());
                userOrderVo.setTotal(value.get(0).getTotal());
                userOrderVo.setId(value.get(0).getId());
                userOrderVo.setEtc(value);
                list1.add(userOrderVo);
            }
        }
        return list1;
    }


    public List<ordersVo> getOrders(Integer pageNum,Integer pageSize){
        List<ordersVo> ordersVos = baseMapper.getAllOrders(pageNum,pageSize);
        return ordersVos;
    }

    @Override
    public Map<Integer, Integer> getOrdersState() {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("state, COUNT(*)");
        queryWrapper.groupBy("state");

        List<Map<String, Object> > result = this.listMaps(queryWrapper);

        Map<Integer, Integer> orderCountsByState = new HashMap<>();
        for (Map<String, Object> map : result) {
            Integer state = (Integer) map.get("state");
            Long count = (Long) map.get("COUNT(*)");
            orderCountsByState.put(state, count.intValue());
        }

        return orderCountsByState;
    }

}
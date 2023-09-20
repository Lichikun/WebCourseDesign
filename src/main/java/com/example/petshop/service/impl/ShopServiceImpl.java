package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.mapDistance;
import com.example.petshop.entity.User;
import com.example.petshop.mapper.ShopMapper;
import com.example.petshop.entity.Shop;
import com.example.petshop.service.ShopService;
import com.example.petshop.vo.shopVo;
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
        shop.setUseful(1);
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
    public Boolean updateUsefulByIds(String ids, Integer flag) {
        //ids  若干个id 用逗号隔开
        String[] aryIds = ids.split(",");
        for(String id: aryIds){

            //查找符合的数据
            UpdateWrapper<Shop> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Shop shop = this.getOne(UpdateWrapper);
            shop.setUseful(flag);

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
    public List<Shop> shopListByValue(String value, String name){
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(value,name);

        return this.list(queryWrapper);
    }
    @Override
    public List<Shop> listByValue (double distance,String longitude,String latitude){
           List<Shop>list=this.list();
           List<Shop>list2=new ArrayList<>();
           double longitude1=Double.parseDouble(longitude);
           double latitude1=Double.parseDouble(latitude);
            for(int i=0;i<list.size();i++){
                double longitude2=Double.parseDouble(list.get(i).getLongitude());
                double laitude2=Double.parseDouble(list.get(i).getLatitude());
                if(mapDistance.GetDistance(longitude1,latitude1,longitude2,laitude2)<=distance*1000) {
                    list2.add(list.get(i));
                }
            }
            return list2;
    }

    @Override
    public Page<Shop> page(Integer pageNum,Integer pageSize,String value,String name) {
        Page<Shop> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(value,name);

        return this.page(page,queryWrapper);
    }

    @Override
    public List<shopVo> search(Integer pageNum, Integer pageSize, String name) {
        List<shopVo> b=baseMapper.search((pageNum-1)*pageSize,pageSize,name);
        return b;
    }

    @Override
    public Shop getById(String id) {
        QueryWrapper<Shop> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return this.getOne(queryWrapper);
    }

    @Override
    public Integer getShopNum() {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        return this.count(queryWrapper);
    }

}
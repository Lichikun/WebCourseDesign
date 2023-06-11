package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.AddressMapper;
import com.example.petshop.entity.Address;
import com.example.petshop.service.AddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper,Address> implements AddressService {


    @Override
    public Boolean add(Address address) {
        this.save(address);
        return true;
    }

    @Override
    public Boolean update(Address address) {
        this.updateById(address);
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
            UpdateWrapper<Address> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Address address = this.getOne(UpdateWrapper);

            //执行
            this.update(address);
        }
        return true;
    }

    @Override
    public Address getByValue(String value,String name){
    QueryWrapper<Address> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Address> listByValue (String value,String name){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Address> page(Integer pageNum,Integer pageSize,String name) {
        Page<Address> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

}
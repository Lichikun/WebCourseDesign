package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.PetsMapper;
import com.example.petshop.entity.Pets;
import com.example.petshop.service.PetsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
    * 宠物表 服务层实现类
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
@Service
public class PetsServiceImpl extends ServiceImpl<PetsMapper,Pets> implements PetsService {


    @Override
    public Boolean add(Pets pets) {
        this.save(pets);
        return true;
    }

    @Override
    public Boolean update(Pets pets) {
        this.updateById(pets);
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
            UpdateWrapper<Pets> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Pets pets = this.getOne(UpdateWrapper);
            pets.setUseful(flag);

            //执行
            this.update(pets);
        }
        return true;
    }

    @Override
    public Pets getByValue(String value,String name){
    QueryWrapper<Pets> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Pets> listByValue (String value,String name){
        QueryWrapper<Pets> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Pets> page(Integer pageNum,Integer pageSize,String value,String name) {
        Page<Pets> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Pets> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(value,name);

        return this.page(page,queryWrapper);
    }

}
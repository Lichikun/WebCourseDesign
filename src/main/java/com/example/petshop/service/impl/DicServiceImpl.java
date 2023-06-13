package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.DicMapper;
import com.example.petshop.entity.Dic;
import com.example.petshop.service.DicService;
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
public class DicServiceImpl extends ServiceImpl<DicMapper,Dic> implements DicService {


    @Override
    public Boolean add(Dic dic) {
        dic.setUseful(1);
        this.save(dic);
        return true;
    }

    @Override
    public Boolean update(Dic dic) {
        this.updateById(dic);
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
            UpdateWrapper<Dic> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Dic dic = this.getOne(UpdateWrapper);
            dic.setUseful(flag);
            //执行
            this.update(dic);
        }
        return true;
    }

    @Override
    public Dic getByValue(String value,String name){
    QueryWrapper<Dic> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Dic> listByValue (String value,String name){
        QueryWrapper<Dic> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Dic> page(Integer pageNum,Integer pageSize,String value,String name) {
        Page<Dic> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Dic> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(value,name);

        return this.page(page,queryWrapper);
    }

}
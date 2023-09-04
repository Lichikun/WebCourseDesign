package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.mapper.PictureMapper;
import com.example.petshop.entity.Picture;
import com.example.petshop.service.PictureService;
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
public class PictureServiceImpl extends ServiceImpl<PictureMapper,Picture> implements PictureService {


    @Override
    public Boolean add(Picture picture) {
        this.save(picture);
        return true;
    }

    @Override
    public Boolean update(Picture picture) {
        this.updateById(picture);
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
            UpdateWrapper<Picture> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Picture picture = this.getOne(UpdateWrapper);

            //执行
            this.update(picture);
        }
        return true;
    }

    @Override
    public Picture getByValue(String value,String name){
    QueryWrapper<Picture> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Picture> listByValue (String value,String name){
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public List<Picture> page(Integer pageNum, Integer pageSize,String belong_id) {
        Page<Picture> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Picture>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("belong_id",belong_id);
        return this.page(page,queryWrapper).getRecords();
    }
}
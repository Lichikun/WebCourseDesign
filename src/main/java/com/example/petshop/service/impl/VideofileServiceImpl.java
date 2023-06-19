package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.common.utils.Result;
import com.example.petshop.mapper.VideofileMapper;
import com.example.petshop.entity.Videofile;
import com.example.petshop.service.VideofileService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class VideofileServiceImpl extends ServiceImpl<VideofileMapper,Videofile> implements VideofileService {


    @Override
    public Boolean add(Videofile videofile) {
        this.save(videofile);
        return true;
    }

    @Override
    public Boolean update(Videofile videofile) {
        this.updateById(videofile);
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
            UpdateWrapper<Videofile> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Videofile videofile = this.getOne(UpdateWrapper);

            //执行
            this.update(videofile);
        }
        return true;
    }

    @Override
    public Videofile getByValue(String value,String name){
    QueryWrapper<Videofile> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Videofile> listByValue (String value,String name){
        QueryWrapper<Videofile> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Videofile> page(Integer pageNum,Integer pageSize,String name) {
        Page<Videofile> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Videofile> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

    @Override

    public Videofile getVideoByBelongId(String belongId) {
        QueryWrapper<Videofile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_id", belongId);
        return getOne(queryWrapper);
    }

    @Override
    public List<Videofile> list() {
        List<Videofile> b=baseMapper.list();
        return b;
    }

    @Override
    public Videofile getById(String id) {
        QueryWrapper<Videofile> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return this.getOne(queryWrapper);
    }

}
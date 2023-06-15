package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.config.SkipTokenValidation;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.DictypeMapper;
import com.example.petshop.entity.Dictype;
import com.example.petshop.service.DictypeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class DictypeServiceImpl extends ServiceImpl<DictypeMapper,Dictype> implements DictypeService {


    @Override
    public Boolean add(Dictype dictype) {
        dictype.setCreateTime(LocalDateTime.now());
        dictype.setUseful(1);
        this.save(dictype);
        return true;
    }

    @Override
    public Boolean update(Dictype dictype) {
        this.updateById(dictype);
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
            UpdateWrapper<Dictype> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Dictype dictype = this.getOne(UpdateWrapper);
            dictype.setUseful(flag);
            //执行
            this.update(dictype);
        }
        return true;
    }

    @Override
    public Dictype getByValue(String value,String name){
    QueryWrapper<Dictype> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Dictype> listByValue (String value,String name){
        QueryWrapper<Dictype> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Dictype> page(Integer pageNum,Integer pageSize,String name) {
        Page<Dictype> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Dictype> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

    @Override
    @SkipTokenValidation
    public List<Dictype> getCategoryDic(String fromPage) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("from_page",fromPage);
        List<Dictype>dic=list(queryWrapper);
        for(int i=0;i<dic.size();i++){
                dic.get(i).setEtc(this.baseMapper.getDicbyid(dic.get(i).getId()));
        }
        return dic;
    }

}
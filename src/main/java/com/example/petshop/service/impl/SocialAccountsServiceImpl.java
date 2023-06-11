package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.mapper.SocialAccountsMapper;
import com.example.petshop.entity.SocialAccounts;
import com.example.petshop.service.SocialAccountsService;
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
public class SocialAccountsServiceImpl extends ServiceImpl<SocialAccountsMapper,SocialAccounts> implements SocialAccountsService {


    @Override
    public Boolean add(SocialAccounts socialAccounts) {
        this.save(socialAccounts);
        return true;
    }

    @Override
    public Boolean update(SocialAccounts socialAccounts) {
        this.updateById(socialAccounts);
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
            UpdateWrapper<SocialAccounts> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            SocialAccounts socialAccounts = this.getOne(UpdateWrapper);

            //执行
            this.update(socialAccounts);
        }
        return true;
    }

    @Override
    public SocialAccounts getByValue(String value,String name){
    QueryWrapper<SocialAccounts> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<SocialAccounts> listByValue (String value,String name){
        QueryWrapper<SocialAccounts> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<SocialAccounts> page(Integer pageNum,Integer pageSize,String name) {
        Page<SocialAccounts> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SocialAccounts> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

}
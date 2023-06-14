package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.JwtTokenProvider;
import com.example.petshop.mapper.CollectMapper;
import com.example.petshop.entity.Collect;
import com.example.petshop.service.CollectService;
import com.example.petshop.vo.collectVoGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.petshop.vo.collectVo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
    * 收藏表，用于会员收藏宠物 服务层实现类
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper,Collect> implements CollectService {
    @Autowired
    private HttpServletRequest request;


    @Override
    public Boolean add(Collect collect) {
        this.save(collect);
        return true;
    }

    @Override
    public Boolean update(Collect collect) {
        this.updateById(collect);
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
            UpdateWrapper<Collect> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Collect collect = this.getOne(UpdateWrapper);

            //执行
            this.update(collect);
        }
        return true;
    }

    @Override
    public Collect getByValue(String value,String name){
    QueryWrapper<Collect> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Collect> listByValue (String value,String name){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public List<collectVo> page(Integer pageNum, Integer pageSize) {
        String token = request.getHeader("Authorization");
        JwtTokenProvider jwtTokenProvider=new JwtTokenProvider();
        String userName=jwtTokenProvider.getUsernameFromToken(token);
        return this.baseMapper.pageByuserName((pageNum-1)*pageSize,pageSize,userName);

    }

    @Override
    public List<collectVoGoods> pageToGetGoods(Integer pageNum, Integer pageSize) {
        String token = request.getHeader("Authorization");
        JwtTokenProvider jwtTokenProvider=new JwtTokenProvider();
        String userName=jwtTokenProvider.getUsernameFromToken(token);
        return this.baseMapper.pageToGetGoods((pageNum-1)*pageSize,pageSize,userName);
    }

}
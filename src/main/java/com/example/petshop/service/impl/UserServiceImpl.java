package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.MD5Utils;
import com.example.petshop.mapper.UserMapper;
import com.example.petshop.entity.User;
import com.example.petshop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* <p>
    * 用户信息 服务层实现类
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    @Override
    public Boolean add(User user) throws Exception {
        user.setUseful(1);
        user.setState(0);

        if(user.getNickname() == "")
            user.setNickname(user.getName());
        String salt = UUID.randomUUID().toString();
        String password = MD5Utils.getEncode(user.getPassword(),salt);

        user.setSalt(salt);
        user.setPassword(password);
        this.save(user);
        return true;
    }


    @Override
    public Boolean checkUser(User user) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",user.getName());
        if(list(queryWrapper).size() == 0)
            return false;
        User user1 = getOne(queryWrapper);
        String checkPsd = MD5Utils.getEncode(user.getPassword(),user1.getSalt());

        if(checkPsd.equals(user1.getPassword()))
            return true;
        return false;
    }

    @Override
    public Boolean update(User user) throws Exception {
        if(!user.getPassword().isEmpty()){
            String salt = UUID.randomUUID().toString();
            String password = MD5Utils.getEncode(user.getPassword(),salt);
            user.setSalt(salt);
            user.setPassword(password);
        }

        this.updateById(user);
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
    public Boolean updateUsefulByIds(String ids, Integer flag) throws Exception {
        //ids  若干个id 用逗号隔开
        String[] aryIds = ids.split(",");
        for(String id: aryIds){

            //查找符合的数据
            UpdateWrapper<User> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            User user = this.getOne(UpdateWrapper);
            user.setUseful(flag);

            //执行
            this.update(user);
        }
        return true;
    }

    @Override
    public User getByValue(String value,String name){
    QueryWrapper<User> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<User> listByValue (String value,String name){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<User> page(Integer pageNum,Integer pageSize,String name) {
        Page<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

}
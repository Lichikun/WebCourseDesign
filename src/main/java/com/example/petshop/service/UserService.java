package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.User;

import java.util.List;

/**
* <p>
    * 用户信息 服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface UserService extends IService<User> {

    Boolean add(User user) throws Exception;
    void deleteByIds(String ids);
    Boolean checkUser(User user) throws Exception;
    Boolean update(User user) throws Exception;
    User getByValue(String value,String name);
    List<User> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Integer flag) throws Exception;
    Page<User> page(Integer pageNum, Integer pageSize, String name);
    Integer getUserNum();
}
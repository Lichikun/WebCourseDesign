package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.SocialAccounts;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface SocialAccountsService extends IService<SocialAccounts> {

    Boolean add(SocialAccounts socialAccounts);
    void deleteByIds(String ids);
    Boolean update(SocialAccounts socialAccounts);
    SocialAccounts getByValue(String value,String name);
    List<SocialAccounts> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<SocialAccounts> page(Integer pageNum, Integer pageSize, String name);
}
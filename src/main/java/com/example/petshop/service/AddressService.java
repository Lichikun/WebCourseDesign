package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Address;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface AddressService extends IService<Address> {

    Boolean add(Address address);
    void deleteByIds(String ids);
    Boolean update(Address address);
    Address getByValue(String value,String name);
    List<Address> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<Address> page(Integer pageNum, Integer pageSize, String name);
}
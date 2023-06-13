package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Dic;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface DicService extends IService<Dic> {

    Boolean add(Dic dic);
    void deleteByIds(String ids);
    Boolean update(Dic dic);
    Dic getByValue(String value,String name);
    List<Dic> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Integer flag);
    Page<Dic> page(Integer pageNum, Integer pageSize, String value,String name);
}
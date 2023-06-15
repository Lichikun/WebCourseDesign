package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Dictype;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface DictypeService extends IService<Dictype> {

    Boolean add(Dictype dictype);
    void deleteByIds(String ids);
    Boolean update(Dictype dictype);
    Dictype getByValue(String value,String name);
    List<Dictype> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Integer flag);
    Page<Dictype> page(Integer pageNum, Integer pageSize, String name);
    List<Dictype>getCategoryDic(String fromPage);
}
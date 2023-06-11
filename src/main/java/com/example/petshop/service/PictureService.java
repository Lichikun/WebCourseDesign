package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Picture;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface PictureService extends IService<Picture> {

    Boolean add(Picture picture);
    void deleteByIds(String ids);
    Boolean update(Picture picture);
    Picture getByValue(String value,String name);
    List<Picture> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<Picture> page(Integer pageNum, Integer pageSize, String name);
}
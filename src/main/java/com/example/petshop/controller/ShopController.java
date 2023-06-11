package com.example.petshop.controller;

import com.example.petshop.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.Shop;
import com.example.petshop.service.ShopService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("//shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Shop shop) {

        Result result = new Result();

        shopService.add(shop);
        result.success("添加成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        shopService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody Shop shop){
        Result result = new Result();

        shopService.update(shop);
        result.success("修改成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Boolean flag) {
        Result result = new Result();
        shopService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(shopService.listByValue(value,name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(shopService.page(pageNum,pageSize,name));
        return result;
    }
}

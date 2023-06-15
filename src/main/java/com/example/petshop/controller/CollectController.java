package com.example.petshop.controller;

import com.example.petshop.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.Collect;
import com.example.petshop.service.CollectService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 收藏表，用于会员收藏宠物 前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Collect collect) {

        Result result = new Result();

        collectService.add(collect);
        result.success("添加成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        collectService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody Collect collect){
        Result result = new Result();

        collectService.update(collect);
        result.success("修改成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Boolean flag) {
        Result result = new Result();
        collectService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(collectService.listByValue(value,name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize){
        Result result = new Result();
        result.success("获取收藏宠物成功");
        result.setData(collectService.page(pageNum,pageSize));
        return result;
    }//获取收藏的宠物
    @RequestMapping(method = RequestMethod.POST,value = "/pageToGetGoods")
    public Result pageToGetGoods( Integer pageNum,Integer pageSize){
        Result result = new Result();
        result.success("获取收藏宠物成功");
        result.setData(collectService.pageToGetGoods(pageNum,pageSize));
        return result;
    }//获取收藏的商品
    @RequestMapping(method = RequestMethod.POST,value = "/isCollect")
    public Result isCollect( String id){
        Result result = new Result();
        result.success("获取收藏宠物成功");
        result.setData(collectService.isCollect(id));
        return result;
    }//判断是否收藏
    @RequestMapping(method = RequestMethod.POST,value = "/addOrDeleteCollect")
    public Result addOrDeleteCollect( String id){//iskeep没用到
        Result result = new Result();
        result.success("获取收藏宠物成功");
        result.setData(collectService.addOrDeleteCollect(id));
        return result;
    }//判断是否收藏
}

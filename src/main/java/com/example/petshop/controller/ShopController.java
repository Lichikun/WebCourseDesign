package com.example.petshop.controller;

import com.example.petshop.common.config.SkipTokenValidation;
import com.example.petshop.common.utils.Result;
import com.example.petshop.service.GoodsService;
import com.example.petshop.service.PetsService;
import com.example.petshop.vo.goodsVo;
import com.example.petshop.vo.petsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.Shop;
import com.example.petshop.service.ShopService;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PetsService petsService;

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
    public Result updateUsefulByIds(String id,Integer flag) {
        Result result = new Result();
        shopService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(double distance,String longitude,String latitude){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(shopService.listByValue(distance,longitude,latitude));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/listShop")
    public Result shopListByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(shopService.shopListByValue(value,name));
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String value, String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(shopService.page(pageNum,pageSize,value,name));
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/search")
    public Result search( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(shopService.search(pageNum,pageSize,name));
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getById")
    public Result getById( String id ){
        Result result = new Result();
        result.success("获取list成功");
        Map<String, Object> map = new HashMap<>();
        Shop a=shopService.getById(id);
        List<goodsVo> goodsvo= goodsService.getByShopid(id);
        List<petsVo> petsvo= petsService.getByShopid(id);
        map.put("shop",a);
        map.put("goods",goodsvo);
        map.put("pets",petsvo);
        result.setData(map);
        return result;
    }

}

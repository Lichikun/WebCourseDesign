package com.example.petshop.controller;

import com.example.petshop.common.config.SkipTokenValidation;
import com.example.petshop.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.Goods;
import com.example.petshop.service.GoodsService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 宠物周边商品表 前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Goods goods) {

        Result result = new Result();

        goodsService.add(goods);
        result.success("添加成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        goodsService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody Goods goods){
        Result result = new Result();

        goodsService.update(goods);
        result.success("修改成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Integer flag) {
        Result result = new Result();
        goodsService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getById")
    public Result getById(String id){
        Result result = new Result();
        result.success("获取商品详情成功");
        result.setData(goodsService.getById(id));
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.listByValue(value,name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String value,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.pageByValue(pageNum,pageSize,value,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/search")
    public Result search( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.search(pageNum,pageSize,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/downQuantitySearch")
    public Result downQuantitySearch( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.downQuantitySearch(pageNum,pageSize,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/upQuantitySearch")
    public Result upQuantitySearch( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.upQuantitySearch(pageNum,pageSize,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/downPriceSearch")
    public Result downPriceSearch( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.downPriceSearch(pageNum,pageSize,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/upPriceSearch")
    public Result upPriceSearch( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.upPriceSearch(pageNum,pageSize,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/homePage")
    public Result homePage( Integer pageNum,Integer pageSize){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.homePage(pageNum,pageSize));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/pageByGoods")
    public Result pageByGoods(String pageFrom,String type,Integer pageNum,Integer pageSize,String orderByDsc,String orderAsc){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.pageByGoods(pageFrom,type,pageNum,pageSize,orderByDsc,orderAsc));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getByShopid")
    public Result getByShopid( String shop_id ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.getByShopid(shop_id));
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getGoodFile")
    public Result getGoodFile (Integer pageNum,Integer pageSize){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.getGoodsFile(pageNum,pageSize));
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getGoodVideoFile")
    public Result getGoodVideoFile (Integer pageNum,Integer pageSize){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(goodsService.getGoodsVideoFile(pageNum,pageSize));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/num")
    public Result num(){
        Result result = new Result();
        result.success("获取num成功");
        result.setData(goodsService.getGoodsNum());
        return result;
    }
}

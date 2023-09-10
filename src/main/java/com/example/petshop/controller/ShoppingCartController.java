package com.example.petshop.controller;

import com.example.petshop.common.utils.Result;
import com.example.petshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.ShoppingCart;
import com.example.petshop.service.ShoppingCartService;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody ShoppingCart shoppingCart) {

        Result result = new Result();
        if(goodsService.checkAvailability(shoppingCart.getGoodsId(),shoppingCart.getGoodsNum(),"check")){
            ShoppingCart findShoppingCart = shoppingCartService.getOneCart(shoppingCart.getUserId(),shoppingCart.getGoodsId());
            if(findShoppingCart != null){
                findShoppingCart.setGoodsNum(findShoppingCart.getGoodsNum()+shoppingCart.getGoodsNum());
                shoppingCartService.update(findShoppingCart);
                result.success("商品重复添加成功");
            } else {
                shoppingCartService.add(shoppingCart);
                result.success("新建商品成功");
            }
        }else{
            result.fail("商品库存不足");
        }

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        shoppingCartService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody ShoppingCart shoppingCart){
        Result result = new Result();
        System.out.println(shoppingCart);
        if(goodsService.checkAvailability(shoppingCart.getGoodsId(),shoppingCart.getGoodsNum(),"check")){
            shoppingCartService.update(shoppingCart);
            result.success("修改成功");
        }else{
            result.fail("超过库存上限");
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Integer flag) {
        Result result = new Result();
        shoppingCartService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(shoppingCartService.listByValue(value,name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(shoppingCartService.page(pageNum,pageSize,name));
        return result;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/getCartList")
    public Result getCartList(String userId){
        Result result = new Result();
        String ids = "";
        List<ShoppingCart> findCart = shoppingCartService.listEqByValue("user_id",userId);
        for(int i = 0;i < findCart.size();i++){
            ids += findCart.get(i).getId() + ",";
        }
        result.setData(shoppingCartService.getOrderList(ids));
        result.success("获取成功");
        return result;
    }

}

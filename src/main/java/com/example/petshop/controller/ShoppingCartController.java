package com.example.petshop.controller;

import com.example.petshop.common.utils.Result;
import com.example.petshop.service.GoodsService;
import com.example.petshop.service.PetsService;
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
    @Autowired
    private PetsService petsService;
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody ShoppingCart shoppingCart) {

        Result result = new Result();
        if(shoppingCart.getType() == 0){
            System.out.println("goods");
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
        }else{
            System.out.println("pets");
            if(petsService.checkAvailability(shoppingCart.getGoodsId(),shoppingCart.getGoodsNum(),"check")){
                ShoppingCart findShoppingCart = shoppingCartService.getOneCart(shoppingCart.getUserId(),shoppingCart.getGoodsId());
                if(findShoppingCart != null){
                    result.fail("达到添加上限");
                } else {
                    shoppingCartService.add(shoppingCart);
                    result.success("新建商品成功");
                }
            }else{
                result.fail("商品库存不足");
            }
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
        if(shoppingCart.getGoodsNum()==null){
            result.success("修改成功");
        }else {
            if (goodsService.checkAvailability(shoppingCart.getGoodsId(), shoppingCart.getGoodsNum(), "check")) {
                shoppingCartService.update(shoppingCart);
                result.success("修改成功");
            } else {
                result.fail("超过库存上限");
            }
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
        String ids_goods = "";
        String ids_pets = "";
        List<ShoppingCart> findCart = shoppingCartService.listEqByValue("user_id",userId);
        for(int i = 0;i < findCart.size();i++){
            if(findCart.get(i).getType() == 0)
                ids_goods += findCart.get(i).getId() + ",";
            else
                ids_pets += findCart.get(i).getId() + ",";
        }
        System.out.println(ids_pets);
        System.out.println(ids_goods);
        result.setData(shoppingCartService.getOrderList(ids_goods));
        result.success("获取成功");
        return result;
    }

}

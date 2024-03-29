package com.example.petshop.controller;

import com.example.petshop.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.Orders;
import com.example.petshop.service.OrdersService;

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
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Orders orders) {

        Result result = new Result();

        result.setData(ordersService.add(orders));
        result.success("添加成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        ordersService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Orders orders) {
        Result result = new Result();

        ordersService.update(orders);
        result.success("修改成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id, Integer flag) {
        Result result = new Result();
        ordersService.updateUsefulByIds(id, flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateState")
    public Result updateState(String id, Integer flag) {
        Result result = new Result();
        ordersService.updateState(id, flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result listByValue(String value, String name) {
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.listByValue(value, name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/page")
    public Result page(Integer pageNum, Integer pageSize, String value, String name) {
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.page(pageNum, pageSize, value, name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getUserOrderByState")
    public Result getUserOrderByState(Integer state) {
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.getUserOrderByState(state));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getOneOrders")
    public Result getOneOrders(String id) {
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.getByOrdersId(id));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getAllOrders")
    public Result getAllOrders(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.getOrders(pageNum, pageSize));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getOrdersState")
    public Result getOrdersState() {
        Result result = new Result();
        result.success("获取orderState成功");
        result.setData(ordersService.getOrdersState());
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getAllOrders_back")
    public Result getAllOrders_back(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.getOrders_back(pageNum, pageSize));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getAllOrders_backByState")
    public Result getAllOrders_backByState(Integer pageNum, Integer pageSize,Integer state) {
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.getOrders_backByState(pageNum,pageSize,state));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getAllOrders_backById")
    public Result getAllOrders_backById(Integer pageNum, Integer pageSize,String ordersId) {
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.getOrders_backById(pageNum, pageSize,ordersId));
        return result;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/setUserOrserState")
    public Result setUserOrserState(String id,Integer state) {
        Result result = new Result();
        result.success("修改成功");
        result.setData(ordersService.setUserOrserState(id,state));
        return result;
    }
      
    @RequestMapping(method = RequestMethod.POST, value = "/setUserOrserReason")
    public Result setUserOrserReason(String id,String reason) {
        Result result = new Result();
        result.success("理由修改成功成功");
        result.setData(ordersService.setUserOrserContent(id,reason));
        return result;
    }
}



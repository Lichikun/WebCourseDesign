package com.example.petshop.controller;

import com.example.petshop.common.config.SkipTokenValidation;
import com.example.petshop.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.User;
import com.example.petshop.service.UserService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody User user) {
        User FindUser = userService.getByValue("username",user.getUsername());
        Result result = new Result();
        //业务 交给业务成 service 去处理
        if(FindUser != null){
            result.fail(user.getUsername()+"已存在");
        }else{
            userService.add(user);
            result.success("添加成功");}

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        userService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody User user){
        Result result = new Result();
        User FindUser = userService.getByValue("name",user.getUsername());
        if(FindUser!=null && !FindUser.getId().equals(user.getId())){
            result.fail("书名"+user.getUsername()+"已存在");
        }else{
            userService.update(user);
            result.success("修改成功");
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Boolean flag) {
        Result result = new Result();
        userService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(userService.listByValue(value,name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(userService.page(pageNum,pageSize,name));
        return result;
    }
}
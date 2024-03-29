package com.example.petshop.controller;

import com.example.petshop.common.config.SkipTokenValidation;
import com.example.petshop.common.utils.Result;
import com.example.petshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.Pets;
import com.example.petshop.service.PetsService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 宠物表 前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/pets")
public class PetsController {
    @Autowired
    private PetsService petsService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Pets pets) {

        Pets FindUser = petsService.getByValue("name",pets.getName());
        Result result = new Result();
        //业务 交给业务成 service 去处理
        if(FindUser != null){
            result.fail(pets.getName()+"已存在");
        }else{
            petsService.add(pets);
            result.success("添加成功");}

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        petsService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody Pets pets){
        Result result = new Result();

        petsService.update(pets);
        result.success("修改成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Integer flag) {
        Result result = new Result();
        petsService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(petsService.listByValue(value,name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String value,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(petsService.page(pageNum,pageSize,value,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getResultById")
    public Result getResultById(String id){
        Result result = new Result();
        result.success("获取宠物信息成功");
        result.setData(petsService.getResultById(id));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/search")
    public Result search( Integer pageNum,Integer pageSize,String type ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(petsService.search(pageNum,pageSize,type));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/downPriceSearch")
    public Result downPriceSearch( Integer pageNum,Integer pageSize,String type ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(petsService.downPriceSearch(pageNum,pageSize,type));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/upPriceSearch")
    public Result upPriceSearch( Integer pageNum,Integer pageSize,String type ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(petsService.upPriceSearch(pageNum,pageSize,type));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/pageByPets")
    public Result pageByPets (String pageFrom,String type,Integer pageNum,Integer pageSize,String orderByDsc,String orderAsc){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(petsService.pageByPets(pageFrom,type,pageNum,pageSize,orderByDsc,orderAsc));
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getPetFile")
    public Result getPetFile (Integer pageNum,Integer pageSize){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(petsService.getPetFile(pageNum,pageSize));
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getVideoFile")
    public Result getVideoFile (Integer pageNum,Integer pageSize){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(petsService.getVideoFile(pageNum,pageSize));
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getPetsPricePhase")
    public Result getPetsPricePhase (){
        Result result = new Result();
        result.success("获取price成功");
        result.setData(petsService.getPetsPricePhase());
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getPetsTypeNum")
    public Result getPetsTypeNum (){
        Result result = new Result();
        result.success("获取typeNum成功");
        result.setData(petsService.getPetsTypeNum());
        return result;
    }
}

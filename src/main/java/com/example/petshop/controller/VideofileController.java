package com.example.petshop.controller;

import com.example.petshop.common.config.SkipTokenValidation;
import com.example.petshop.common.utils.Result;
import com.example.petshop.service.GoodsService;
import com.example.petshop.service.PetsService;
import com.example.petshop.vo.goodsVo;
import com.example.petshop.vo.petsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.Videofile;
import com.example.petshop.service.VideofileService;

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
@RequestMapping("/videofile")
public class VideofileController {

    @Autowired
    private VideofileService videofileService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PetsService petsService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Videofile videofile) {

        Result result = new Result();

        videofileService.add(videofile);
        result.success("添加成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        videofileService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody Videofile videofile){
        Result result = new Result();

        videofileService.update(videofile);
        result.success("修改成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Boolean flag) {
        Result result = new Result();
        videofileService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(videofileService.listByValue(value,name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(videofileService.page(pageNum,pageSize,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getVideoByBelongId")
    public Result getVideoByBelongId(String belongId) {
        Result result = new Result();
        result.setData(videofileService.getVideoByBelongId(belongId));
        result.success("获取成功");
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/List")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(videofileService.list());
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getGoodsById")
    public Result getGoodsById( String id ){
        Result result = new Result();
        result.success("获取list成功");
        Map<String, Object> map = new HashMap<>();
        Videofile a=videofileService.getById(id);
        map.put("video",a);
        String ids=a.getBelongId();
        List<goodsVo> goodsvo= goodsService.getByIds(ids);
        map.put("goods",goodsvo);
        result.setData(map);
        return result;
    }

    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/getPetsById")
    public Result getPetsById( String id ){
        Result result = new Result();
        result.success("获取list成功");
        Map<String, Object> map = new HashMap<>();
        Videofile a=videofileService.getById(id);
        map.put("video",a);
        String ids=a.getBelongId();
        List<petsVo> petsvo= petsService.getByIds(ids);
        map.put("pets",petsvo);
        result.setData(map);
        return result;
    }
}

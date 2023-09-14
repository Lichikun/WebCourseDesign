package com.example.petshop.controller;

import com.example.petshop.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.petshop.entity.Evaluation;
import com.example.petshop.service.EvaluationService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 评价表 前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Evaluation evaluation) {

        Result result = new Result();

        evaluationService.add(evaluation);
        result.success("添加成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        evaluationService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody Evaluation evaluation){
        Result result = new Result();

        evaluationService.update(evaluation);
        result.success("修改成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Boolean flag) {
        Result result = new Result();
        evaluationService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(evaluationService.listByValue(value,name));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String name ){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(evaluationService.page(pageNum,pageSize,name));
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/getUserComment")
    public Result getUserComment(String ids,String orderId){
        Result result = new Result();
        result.success("评论获取成功");
        result.setData(evaluationService.getUserComment(ids,orderId));
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/saveUserComment")
    public Result saveUserComment(String content,String goodsId,Integer score,String orderId){
        Result result = new Result();
        result.success("评论保存成功");
        result.setData(evaluationService.saveUserComment(content,goodsId,score,orderId));
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/getGoodsComment")
    public Result getGoodsComment(String goodsId){
        Result result = new Result();
        result.success("评论获取成功");
        result.setData(evaluationService.getGoodsComment(goodsId));
        return result;
    }
}

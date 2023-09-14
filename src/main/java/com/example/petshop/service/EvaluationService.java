package com.example.petshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petshop.entity.Evaluation;
import com.example.petshop.vo.commentVo;

import java.util.List;

/**
* <p>
    * 评价表 服务类接口
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
public interface EvaluationService extends IService<Evaluation> {

    Boolean add(Evaluation evaluation);
    void deleteByIds(String ids);
    Boolean update(Evaluation evaluation);
    Evaluation getByValue(String value,String name);
    List<Evaluation> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<Evaluation> page(Integer pageNum, Integer pageSize, String name);
    List<Evaluation> getUserComment(String ids,String orderId);
    Boolean saveUserComment(String content,String goodsId,Integer score,String orderId);
    List<commentVo>getGoodsComment(String goodsId);
}
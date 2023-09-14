package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import com.example.petshop.common.utils.JwtTokenProvider;
import com.example.petshop.mapper.EvaluationMapper;
import com.example.petshop.entity.Evaluation;
import com.example.petshop.service.EvaluationService;
import com.example.petshop.vo.commentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
    * 评价表 服务层实现类
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper,Evaluation> implements EvaluationService {
    @Autowired
    private HttpServletRequest request;

    @Override
    public Boolean add(Evaluation evaluation) {
        this.save(evaluation);
        return true;
    }

    @Override
    public Boolean update(Evaluation evaluation) {
        this.updateById(evaluation);
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }

    @Override
    public Boolean updateUsefulByIds(String ids, Boolean flag) {
        //ids  若干个id 用逗号隔开
        String[] aryIds = ids.split(",");
        for(String id: aryIds){

            //查找符合的数据
            UpdateWrapper<Evaluation> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Evaluation evaluation = this.getOne(UpdateWrapper);

            //执行
            this.update(evaluation);
        }
        return true;
    }

    @Override
    public Evaluation getByValue(String value,String name){
    QueryWrapper<Evaluation> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Evaluation> listByValue (String value,String name){
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Evaluation> page(Integer pageNum,Integer pageSize,String name) {
        Page<Evaluation> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

    @Override
    public List<Evaluation> getUserComment(String ids,String orderId) {
        String token = request.getHeader("Authorization");
        JwtTokenProvider jwtTokenProvider=new JwtTokenProvider();
        String userName=jwtTokenProvider.getUsernameFromToken(token);
        String userId=this.baseMapper.getUserId(userName);
        List<Evaluation>list=new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
           Evaluation evaluation=this.baseMapper.getComment(id,userId,orderId);
           list.add(evaluation);
        }
        return list;
    }

    @Override
    public Boolean saveUserComment(String content, String goodsId, Integer score,String orderId) {
        String token = request.getHeader("Authorization");
        JwtTokenProvider jwtTokenProvider=new JwtTokenProvider();
        String userName=jwtTokenProvider.getUsernameFromToken(token);
        String userId=this.baseMapper.getUserId(userName);
        String shopId=this.baseMapper.getGooodsShopId(goodsId);
        Evaluation evaluation=new Evaluation();
        evaluation.setUseful(1);
        evaluation.setContent(content);
        evaluation.setGoodsId(goodsId);
        evaluation.setScore(score);
        evaluation.setTime(DateTool.getCurrTime());
        evaluation.setUserId(userId);
        evaluation.setShopId(shopId);
        evaluation.setOrderId(orderId);
        this.save(evaluation);
        return true;
    }

    @Override
    public List<commentVo> getGoodsComment(String goodsId) {

        return this.baseMapper.getGooodsComment(goodsId);
    }

}
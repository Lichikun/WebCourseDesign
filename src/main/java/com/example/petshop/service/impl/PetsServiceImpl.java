package com.example.petshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.mapper.PetsMapper;
import com.example.petshop.entity.Pets;
import com.example.petshop.service.PetsService;
import com.example.petshop.vo.petsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
    * 宠物表 服务层实现类
    * </p>
*
* @author YKH
* @since 2023-06-11
*/
@Service
public class PetsServiceImpl extends ServiceImpl<PetsMapper,Pets> implements PetsService {


    @Override
    public Boolean add(Pets pets) {
        pets.setStock(1);
        pets.setUseful(1);
        this.save(pets);
        return true;
    }

    @Override
    public Boolean update(Pets pets) {
        this.updateById(pets);
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            baseMapper.petsMultiDelete(id);
        }
    }

    @Override
    public Boolean updateUsefulByIds(String ids, Integer flag) {
        //ids  若干个id 用逗号隔开
        String[] aryIds = ids.split(",");
        for(String id: aryIds){

            //查找符合的数据
            UpdateWrapper<Pets> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            Pets pets = this.getOne(UpdateWrapper);
            pets.setUseful(flag);

            //执行
            this.update(pets);
        }
        return true;
    }

    @Override
    public Pets getByValue(String value,String name){
    QueryWrapper<Pets> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<Pets> listByValue (String value,String name){
        QueryWrapper<Pets> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<Pets> page(Integer pageNum,Integer pageSize,String value,String name) {
        Page<Pets> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Pets> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(value,name);

        return this.page(page,queryWrapper);
    }

    @Override
    public List<petsVo> getResultById(String id) {
        return this.baseMapper.getById(id);
    }

    @Override
    public List<petsVo> search(Integer pageNum, Integer pageSize,String type) {
        List<petsVo> b=baseMapper.search((pageNum-1)*pageSize,pageSize,type);
        return b;
    }

    @Override
    public List<petsVo> downPriceSearch(Integer pageNum, Integer pageSize,String type) {
        List<petsVo> b= baseMapper.downPriceSearch((pageNum-1)*pageSize,pageSize,type);
        return b;
    }

    @Override
    public List<petsVo> upPriceSearch(Integer pageNum, Integer pageSize,String type) {
        List<petsVo> b=baseMapper.upPriceSearch((pageNum-1)*pageSize,pageSize,type);
        return b;
    }

    @Override
    public List<petsVo> pageByPets(String pageFrom, String type, Integer pageNum, Integer pageSize, String orderByDsc, String orderAsc) {
        if(pageFrom.equals("home")){
            return this.baseMapper.getHomePagePets((pageNum-1)*pageSize,pageSize,orderByDsc,orderAsc);
        }else{
            return this.baseMapper.getCategoryPagePets((pageNum-1)*pageSize,pageSize,type,orderByDsc,orderAsc);
        }
    }

    @Override
    public List<petsVo> getByShopid(String shop_id) {
        List<petsVo> b=baseMapper.getByShopid(shop_id);
        return b;
    }

    @Override
    public List<petsVo> getByIds(String id) {
        List<petsVo> b=baseMapper.getByIds(id);
        return b;
    }

    @Override
    public List<petsVo> getPetFile(Integer pageNum,Integer pageSize) {
        List<petsVo> list = baseMapper.getPetFile(pageNum,pageSize);
        return list;
    }

    @Override
    public List<petsVo> getVideoFile(Integer pageNum,Integer pageSize) {
        List<petsVo> list = baseMapper.getVideoFile(pageNum,pageSize);
        return list;
    }
}
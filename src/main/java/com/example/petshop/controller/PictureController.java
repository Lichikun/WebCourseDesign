package com.example.petshop.controller;

import com.example.petshop.common.config.SkipTokenValidation;
import com.example.petshop.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;

import com.example.petshop.entity.Picture;
import com.example.petshop.service.PictureService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YKH
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Picture picture) {

        Result result = new Result();

        pictureService.add(picture);
        result.success("添加成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        pictureService.deleteByIds(ids);
        result.success("删除成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody Picture picture){
        Result result = new Result();

        pictureService.update(picture);
        result.success("修改成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String id,Boolean flag) {
        Result result = new Result();
        pictureService.updateUsefulByIds(id,flag);
        result.success("更新成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result listByValue(String value,String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(pictureService.listByValue(value,name));
        return result;
    }
    @SkipTokenValidation
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String belong_id){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(pictureService.page(pageNum,pageSize,belong_id));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/uploadAvatar")
    public Result editImg(MultipartFile file) throws Exception {
        Result result = new Result();
        if(file.isEmpty()){
            result.fail("图片上传失败");
        }
        String originalFilename = file.getOriginalFilename();
        String ext = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-","");
        String filename = uuid + ext;
        String pre = "C:/image/avatar/";
        String path = pre + filename;
        file.transferTo(new File(path));
        result.setData(filename);
        result.success("保存成功");
        return result;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/uploadCarousel")
    public Result editImg1(MultipartFile file) throws Exception {
        Result result = new Result();
        if(file.isEmpty()){
            result.fail("图片上传失败");
        }
        String originalFilename = file.getOriginalFilename();
        String ext = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-","");
        String filename = uuid + ext;
        System.out.println(filename);
        String pre = "C:/image/carousel/";
        String path = pre + filename;
        file.transferTo(new File(path));
        result.setData(filename);
        result.success("保存成功");
        return result;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/uploadGoods")
    public Result editImg2(MultipartFile file) throws Exception {
        Result result = new Result();
        if(file.isEmpty()){
            result.fail("图片上传失败");
        }
        String originalFilename = file.getOriginalFilename();
        String ext = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-","");
        String filename = uuid + ext;
        System.out.println(filename);
        String pre = "C:/image/goods/";
        String path = pre + filename;
        file.transferTo(new File(path));
        result.setData(filename);
        result.success("保存成功");
        return result;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/uploadPet")
    public Result editImg3(MultipartFile file) throws Exception {
        Result result = new Result();
        if(file.isEmpty()){
            result.fail("图片上传失败");
        }
        String originalFilename = file.getOriginalFilename();
        String ext = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-","");
        String filename = uuid + ext;
        System.out.println(filename);
        String pre = "C:/image/pet/";
        String path = pre + filename;
        file.transferTo(new File(path));
        result.setData(filename);
        result.success("保存成功");
        return result;
    }


}

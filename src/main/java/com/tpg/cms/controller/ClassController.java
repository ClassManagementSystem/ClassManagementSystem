package com.tpg.cms.controller;
import com.tpg.cms.model.*;
import com.tpg.cms.service.ArticleService;
import com.tpg.cms.service.ClassService;
import com.tpg.cms.utils.Page;
import com.tpg.cms.utils.ResultAQ;
import com.tpg.cms.utils.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/class")
@CrossOrigin
@Api(tags = "班级管理")
public class ClassController {

    @Autowired
    ClassService classService;

    //新建班级
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ClmsClass clmsClass){
        try {
            classService.save(clmsClass);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR,"保存失败");
        }
        return new ResultAQ<>("保存成功");
    }

    //根据id删除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> deleteById(@PathVariable("id") Integer id){
        try {
            classService.deleteById(id);
        }catch (Exception e){
            return new ResultAQ<>("删除失败");
        }
        return new ResultAQ<>("删除成功");
    }

    //根据id查询
    @GetMapping("/get/{id}")
    public ResultAQ<ClmsClass> getById(@PathVariable("id") Integer id){
        ClmsClass clmsClass;
        try {
            clmsClass = classService.getById(id);
        }catch (Exception e){
            return new ResultAQ<>("查询失败");
        }
        return new ResultAQ<>(clmsClass);
    }

    //更新班级
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ClmsClass clmsClass){
        try {
            classService.update(clmsClass);
        }catch (Exception e){
            return new ResultAQ<>("修改失败");
        }
        return new ResultAQ<>("修改成功");
    }

    //分页查询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsClass>> getByPage(@RequestBody Page<ClmsClass> page){
        page = classService.getByPage(page);
        return new ResultAQ<>(page);
    }

}

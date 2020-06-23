package com.tpg.cms.controller;
import com.tpg.cms.model.ClmsClass;
import com.tpg.cms.model.ClmsGroup;
import com.tpg.cms.model.ClmsUser;
import com.tpg.cms.service.ClassService;
import com.tpg.cms.service.GroupService;
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
@RequestMapping("/group")
@CrossOrigin
@Api(tags = "小组管理")
public class GroupController {

    @Autowired
    GroupService groupService;

    //新建小组
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ClmsGroup clmsGroup){
        try {
            groupService.save(clmsGroup);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR,"保存失败");
        }
        return new ResultAQ<>("保存成功");
    }

    //根据id删除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> deleteById(@PathVariable("id") Integer id){
        try {
            groupService.deleteById(id);
        }catch (Exception e){
            return new ResultAQ<>("删除失败");
        }
        return new ResultAQ<>("删除成功");
    }

    //根据id查询
    @GetMapping("/get/{id}")
    public ResultAQ<ClmsGroup> getById(@PathVariable("id") Integer id){
        ClmsGroup clmsGroup;
        try {
            clmsGroup = groupService.getById(id);
        }catch (Exception e){
            return new ResultAQ<>("查询失败");
        }
        return new ResultAQ<>(clmsGroup);
    }

    //更新小组
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ClmsGroup clmsGroup){
        try {
            groupService.update(clmsGroup);
        }catch (Exception e){
            return new ResultAQ<>("修改失败");
        }
        return new ResultAQ<>("修改成功");
    }

    //分页查询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsGroup>> getByPage(@RequestBody Page<ClmsGroup> page){
        page = groupService.getByPage(page);
        return new ResultAQ<>(page);
    }

    @PostMapping("getAll")
    public ResultAQ<List<ClmsGroup>> getAll(){
        return new ResultAQ<>(groupService.getAll());
    }

}

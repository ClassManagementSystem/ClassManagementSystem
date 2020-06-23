package com.tpg.cms.controller;

import com.tpg.cms.model.ArticleStatistics;
import com.tpg.cms.model.ArticleType;
import com.tpg.cms.service.ArticleTypeService;
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
@RequestMapping("/articleType")
@CrossOrigin
@Api(tags = "类型管理")
public class ArticleTypeController {

    @Autowired
    ArticleTypeService articleTypeService;

    //新增类型
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ArticleType articleType){
        try {
            articleTypeService.save(articleType);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR,"保存失败");
        }
        return new ResultAQ<>("保存成功");
    }

    //根据id查询
    @GetMapping("/get/{id}")
    public ResultAQ<ArticleType> getById(@PathVariable("id") Integer id){
        ArticleType articleType;
        try {
            articleType = articleTypeService.getById(id);
        }catch (Exception e){
            return new ResultAQ<>("查询失败");
        }
        return new ResultAQ<>(articleType);
    }

    //根据id删除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> deleteById(@PathVariable("id") Integer id){
        try {
            articleTypeService.deleteById(id);
        }catch (Exception e){
            return new ResultAQ<>("删除失败");
        }
        return new ResultAQ<>("删除成功");
    }

    //更新文章类型
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ArticleType articleType){
        try {
            articleTypeService.update(articleType);
        }catch (Exception e){
            return new ResultAQ<>("修改失败");
        }
        return new ResultAQ<>("修改成功");
    }

    //分页查询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ArticleType>> getByPage(@RequestBody Page<ArticleType> page){
        page = articleTypeService.getByPage(page);
        return new ResultAQ<>(page);
    }

    //查询各类型对应的文章数以及占比
    @PostMapping("/getArticleTypeCountInfo")
    public ResultAQ<Page<ArticleStatistics>> getArticleTypeCountInfo(@RequestBody Page<ArticleStatistics> page){
        String sortColumn = page.getSortColumn();
        page.setSortColumn(sortColumn);
        if(StringUtils.isEmpty(sortColumn)){
            String[] sortColumns = {"type_count"};
            List<String> sortList = Arrays.asList(sortColumns);
            if(sortList.contains(sortColumn.toLowerCase())){
                return new ResultAQ<>(ResultCode.ERROR,"参数错误");
            }
        }
        page = articleTypeService.getArticleTypeCountInfo(page);
        return new ResultAQ<>(page);
    }

}

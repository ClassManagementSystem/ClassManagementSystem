package com.tpg.cms.controller;

import com.tpg.cms.model.ArticleStatistics;
import com.tpg.cms.model.ClmsArticle;
import com.tpg.cms.service.ArticleService;
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
@RequestMapping("/article")
@CrossOrigin
@Api(tags = "文章管理")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    //新建文章
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ClmsArticle clmsArticle){
        try {
            articleService.save(clmsArticle);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR,"保存失败");
        }
        return new ResultAQ<>("保存成功");
    }

    //根据id删除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> deleteById(@PathVariable("id") Integer id){
        try {
            articleService.deleteById(id);
        }catch (Exception e){
            return new ResultAQ<>("删除失败");
        }
        return new ResultAQ<>("删除成功");
    }

    //根据id查询
    @GetMapping("/get/{id}")
    public ResultAQ<ClmsArticle> getById(@PathVariable("id") Integer id){
        ClmsArticle clmsArticle;
        try {
            clmsArticle = articleService.getById(id);
        }catch (Exception e){
            return new ResultAQ<>("查询失败");
        }
        return new ResultAQ<>(clmsArticle);
    }

    //更新文章
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ClmsArticle clmsArticle){
        try {
            articleService.update(clmsArticle);
        }catch (Exception e){
            return new ResultAQ<>("修改失败");
        }
        return new ResultAQ<>("修改成功");
    }

    //分页查询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsArticle>> getByPage(@RequestBody Page<ClmsArticle> page){
        page = articleService.getByPage(page);
        return new ResultAQ<>(page);
    }

    //获取用户文章类型信息
    @PostMapping("/getUserArticleCountInfo")
    public ResultAQ<Page<ArticleStatistics>> getUserArticleCountInfo(@RequestBody Page<ArticleStatistics> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 下划线的 排序列
        page.setSortColumn(sortColumn);
        // 判断排序列不为空
        if(StringUtils.isEmpty(sortColumn)){
            // 对应类型的文章量
            String[] sortColumns = {"article_type"};
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(sortColumn.toLowerCase())) {
                return new ResultAQ<>(ResultCode.ERROR,"参数错误");
            }
        }
        page = articleService.getUserArticleCountInfo(page);
        return new ResultAQ<>(page);
    }

    //查询发表作品前三的用户文章数据信息
    @PostMapping("/getArticleCountInfo")
    public ResultAQ<Page<ArticleStatistics>> getArticleCountInfo(@RequestBody Page<ArticleStatistics> page){
        String sortColumn = page.getSortColumn();
        page.setSortColumn(sortColumn);
        if(StringUtils.isEmpty(sortColumn)){
            // 文章数 、文章点赞量、文章评论量
            String[] sortColumns = {"articleCount", "likeCount", "commentCount"};
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(sortColumn.toLowerCase())) {
                return new ResultAQ<>(ResultCode.ERROR,"参数错误");
            }
        }
        page = articleService.getArticleCountInfo(page);
        return new ResultAQ<>(page);
    }

}

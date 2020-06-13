package com.tpg.cms.controller;

import com.tpg.cms.model.ClmsComment;
import com.tpg.cms.service.CommentService;
import com.tpg.cms.utils.Page;
import com.tpg.cms.utils.ResultAQ;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin
@Api(tags = "评论管理")
public class CommentController {

    @Autowired
    CommentService commentService;

    //根据id删除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> deleteById(@PathVariable("id") Integer id){
        try {
            commentService.deleteById(id);
        }catch (Exception e){
            return new ResultAQ<>("删除失败");
        }
        return new ResultAQ<>("删除成功");
    }

    //分页查询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsComment>> getByPage(@RequestBody Page<ClmsComment> page){
        page = commentService.getByPage(page);
        return new ResultAQ<>(page);
    }
}

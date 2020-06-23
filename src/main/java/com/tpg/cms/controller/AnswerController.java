package com.tpg.cms.controller;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.service.AnswerService;
import com.tpg.cms.utils.Page;
import com.tpg.cms.utils.ResultAQ;
import com.tpg.cms.utils.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/answer")
@CrossOrigin
@Api(tags = "答疑管理")
public class AnswerController {

//    @Autowired
//    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    // 保 存， 添 加 数 据
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ClmsAnswer clmsAnswer){
        try{
            // clmsAnswer.setAnswer_author(answerService.getUserName());
            answerService.save(clmsAnswer);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，保存失败，请稍后再试！");
        }
        return new ResultAQ<>("保存成功!");
    }

    // 根 据 id 删 除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> delete(@PathVariable("id") Integer id){
        try{
            answerService.deleteById(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，删除失败，请稍后再试！");
        }
        return new ResultAQ<>("删除成功!");
    }

    // 根 据 id 恢 复 已 删 除 的
    @DeleteMapping("/restore/{id}")
    public ResultAQ<Object> restore(@PathVariable("id") Integer id){
        try{
            answerService.restoreById(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，恢复失败，请稍后再试！");
        }
        return new ResultAQ<>("恢复成功!");
    }

    // 更 新 修 改 数 据
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ClmsAnswer clmsAnswer){
        try{
            answerService.update(clmsAnswer);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，修改失败，请稍后再试！");
        }
        return new ResultAQ<>("修改成功!");
    }

    // 设置为已采纳
    @PutMapping("/Adopted/{id}")
    public ResultAQ<Object> Adopted(@PathVariable Integer id){
        try{
            answerService.Adopted(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，修改失败，请稍后再试！");
        }
        return new ResultAQ<>("答复状态更新成功:已采纳!");
    }

    //设置为 未采纳
    @PutMapping("/notAdopt/{id}")
    public ResultAQ<Object> notAdopt(@PathVariable Integer id){
        try{
            answerService.notAdopted(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，修改失败，请稍后再试！");
        }
        return new ResultAQ<>("答复状态更新成功:未采纳!");
    }

    // 根 据 id 查 询
    @GetMapping("/get/{id}")
    public ResultAQ<ClmsAnswer> get(@PathVariable("id") Integer id){
        ClmsAnswer clmsAnswer;
        try{
            clmsAnswer = answerService.getById(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，查询失败，请稍后再试！");
        }
        return new ResultAQ<>(clmsAnswer);
    }

    // 根 据 id 查 询 --- 查 询 未 删 除 的 --- 预 留 给 前 台 的 接 口
    @GetMapping("/getNotDeleted/{id}")
    public ResultAQ<ClmsAnswer> getDeleted(@PathVariable("id") Integer id){
        ClmsAnswer clmsAnswer;
        try{
            clmsAnswer = answerService.getNotDeletedById(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，查询失败，请稍后再试！");
        }
        return new ResultAQ<>(clmsAnswer);
    }

    // 分 页 查 询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsAnswer>> getByPage(@RequestBody Page<ClmsAnswer> page){
        String sortColumn = page.getSortColumn();
        page.setSortColumn(sortColumn);
        if(sortColumn.isEmpty()){
            // 点赞数量 提问时间，修改时间
            String[] sortColumns = {"answer_good","answer_time", "answer_update"};
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(sortColumn.toLowerCase())) {
                return new ResultAQ<>(ResultCode.ERROR, "排序参数错误！");
            }
        }
        page = answerService.getByPage(page);
        return new ResultAQ<>(page);
    }
}

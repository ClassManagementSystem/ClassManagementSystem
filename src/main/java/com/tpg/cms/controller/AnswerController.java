package com.tpg.cms.controller;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.service.AnswerService;
import com.tpg.cms.service.QuestionService;
import com.tpg.cms.utils.Page;
import com.tpg.cms.utils.ResultAQ;
import com.tpg.cms.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    // 保 存， 添 加 数 据
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ClmsAnswer clmsAnswer){
        clmsAnswer.setAnswer_author(answerService.getUserName());
        answerService.save(clmsAnswer);
        return new ResultAQ<>("保存成功!");
    }

    // 根据id删除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> delete(@PathVariable("id") Integer id){
        answerService.deleteById(id);
        return new ResultAQ<>("删除成功!");
    }

    // 更 新 修 改 数 据
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ClmsAnswer clmsAnswer){
        answerService.update(clmsAnswer);
        return new ResultAQ<>("修改成功!");
    }

    // 设置为已采纳
    @PutMapping("/Adopted/{id}")
    public ResultAQ<Object> Adopted(@PathVariable Integer id){
        answerService.Adopted(id);
        return new ResultAQ<>("答复状态更新成功:已采纳!");
    }

    //设置为 未采纳
    @PutMapping("/notAdopt/{id}")
    public ResultAQ<Object> notAdopt(@PathVariable Integer id){
        answerService.notAdopted(id);
        return new ResultAQ<>("答复状态更新成功:未采纳!");
    }

    // 根据id查询
    @GetMapping("/get/{id}")
    public ResultAQ<ClmsAnswer> get(@PathVariable("id") Integer id){
        ClmsAnswer clmsAnswer = answerService.getById(id);
        return new ResultAQ<>(clmsAnswer);
    }

    // 分 页 查 询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsAnswer>> getByPage(@RequestBody Page<ClmsAnswer> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 下划线的 排序列
        page.setSortColumn(sortColumn);
        // 判断排序列不为空
        if(sortColumn.isEmpty()){
            // 点赞数量 提问时间，修改时间
            String[] sortColumns = {"answer_good","answer_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(sortColumn.toLowerCase())) {
                return new ResultAQ<>(ResultCode.ERROR, "参数错误！");
            }
        }
        page = answerService.getByPage(page);
        return new ResultAQ<>(page);
    }
}

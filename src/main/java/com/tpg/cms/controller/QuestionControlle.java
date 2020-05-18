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
@RequestMapping("/question")
public class QuestionControlle {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    // 保 存，新 增 数 据
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ClmsQuestion clmsQuestion){
        clmsQuestion.setQuestion_author(answerService.getUserName());
        questionService.save(clmsQuestion);
        return new ResultAQ<>("保存成功!");
    }

    //根 据 id 删 除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> delete(@PathVariable("id") Integer id){
        questionService.deleteById(id);
        return new ResultAQ<>("删除成功!");
    }

    // 更 新 数 据
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ClmsQuestion clmsQuestion){
        questionService.update(clmsQuestion);
        return new ResultAQ<>("修改成功!");
    }

    //设置问题为已解决
    @PutMapping("/isSolve/{id}")
    public ResultAQ<Object> isSolve(@PathVariable Integer id){
        questionService.isSolve(id);
        return new ResultAQ<>("问题状态更新成功: 已解决!");
    }

    //设置问题为未解决
    @PutMapping("/noSolve/{id}")
    public ResultAQ<Object> noSolve(@PathVariable Integer id){
        questionService.noSolve(id);
        return new ResultAQ<>("问题状态更新成功:未解答!");
    }

    //分页查询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsQuestion>> getByPage(@RequestBody Page<ClmsQuestion> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 下划线的 排序列
        page.setSortColumn(page.getSortColumn());
        // 判断排序列不为空
        if(sortColumn.isEmpty()){
            // 点赞数量 问题答复量 提问时间，修改时间
            String[] sortColumns = {"question_good", "answer_count", "question_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(sortColumn.toLowerCase())) {
                return new ResultAQ<>(ResultCode.ERROR, "参数错误！");
            }
        }
        page = questionService.getByPage(page);
        return new ResultAQ<>(page);
    }

    // 根 据 id 查 询
    @GetMapping("/get/{id}")
    public ResultAQ<ClmsQuestion> get(@PathVariable("id") Integer id){
        return new ResultAQ<>(questionService.getById(id));
    }

}

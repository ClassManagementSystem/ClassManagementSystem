package com.tpg.cms.controller;

import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.service.QuestionService;
import com.tpg.cms.utils.Page;
import com.tpg.cms.utils.ResultAQ;
import com.tpg.cms.utils.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin
@Api(tags = "问答管理")
public class QuestionControlle {

    @Autowired
    private QuestionService questionService;

//    @Autowired
//    private AnswerService answerService;

    // 保 存，新 增 数 据
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ClmsQuestion clmsQuestion){
        try{
            // clmsQuestion.setQuestion_author(answerService.getUserName());
            questionService.save(clmsQuestion);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，保存失败，请稍后再试！");
        }
        return new ResultAQ<>("保存成功!");
    }

    //根 据 id 删 除
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> delete(@PathVariable("id") Integer id){
        try{
            questionService.deleteById(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，删除失败，请稍后再试！");
        }
        return new ResultAQ<>("删除成功!");
    }

    // 根 据 id 恢 复 已 删 除 的
    @PutMapping("/restore/{id}")
    public ResultAQ<Object> restore(@PathVariable("id") Integer id){
        try{
            questionService.restoreById(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，恢复失败，请稍后再试！");
        }
        return new ResultAQ<>("恢复成功!");
    }

    // 更 新 数 据
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ClmsQuestion clmsQuestion){
        try{
            questionService.update(clmsQuestion);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，修改失败，请稍后再试！");
        }
        return new ResultAQ<>("修改成功!");
    }

    //设置问题为已解决
    @PutMapping("/isSolve/{id}")
    public ResultAQ<Object> isSolve(@PathVariable Integer id){
        try{
            questionService.isSolve(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，修改失败，请稍后再试！");
        }
        return new ResultAQ<>("问题状态更新成功: 已解决!");
    }

    //设置问题为未解决
    @PutMapping("/noSolve/{id}")
    public ResultAQ<Object> noSolve(@PathVariable Integer id){
        try{
            questionService.noSolve(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，修改失败，请稍后再试！");
        }
        return new ResultAQ<>("问题状态更新成功:未解答!");
    }

    //分页查询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsQuestion>> getByPage(@RequestBody Page<ClmsQuestion> page){
        String sortColumn = page.getSortColumn();
        page.setSortColumn(page.getSortColumn());
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

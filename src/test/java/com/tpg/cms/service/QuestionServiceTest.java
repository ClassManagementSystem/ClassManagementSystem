package com.tpg.cms.service;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    /*
     *  添 加 数 据
     */
    @Test
    public void testSave(){
        ClmsQuestion clmsQuestion = new ClmsQuestion();
        // id 自 增 长
        clmsQuestion.setQuestion_title("第七个问题");  // 问 题 标 题
        clmsQuestion.setQuestion_content("第七个问题的内容"); // 问 题 内 容
        clmsQuestion.setQuestion_author("测试人员"); // 问 题 作 者
        // 创建时间 question_time  更新时间 update_time  会 根 据 数 据 的 插 入 或  更 改 自  动 改 变
        // 点赞数量question_good 答复数量answer_count 解答状态 question_mark 是否删除 question_status 默 认 值 都 为 0
        questionService.save(clmsQuestion);
    }

    /*
     *  根 据 答 复 id 查 询 问 题
     */
    @Test
    public void getByIdTest(){
        Integer id = 1;
        System.out.println(questionService.getById(id).toString());

    }

    /*
     *  根 据 问 题 id 删 除
     */
    @Test
    public void testDeletedById(){
        questionService.deleteById(1);
    }

    /*
     *  根 据 问 题 id 恢 复 已 删 除
     */
    @Test
    public void testRestoreById(){
        questionService.restoreById(1);
    }


    /*
     *  根 据 问 题 id 更 新 数 据
     */
    @Test
    public void testUpdate(){
        ClmsQuestion clmsQuestion = new ClmsQuestion();
        clmsQuestion.setQuestion_id(3); // 问 题 id
        clmsQuestion.setQuestion_title("第三个问题");  // 问 题 标 题
        clmsQuestion.setQuestion_content("第三个问题的内容"); // 问 题 内 容
        clmsQuestion.setQuestion_author("维护人员"); // 问 题 作 者
        clmsQuestion.setQuestion_mark(1); // 解答状态 question_mark
        // 创建时间 question_time  更新时间 update_time  会 根 据 数 据 的 插 入 或  更 改 自  动 改 变
        // 点赞数量question_good 答复数量answer_count  是否删除 question_status 默 认 值 都 为 0
        questionService.update(clmsQuestion);
    }

    /*
     *  根 据 问 题 id 设 置 为 已 解 决
     */
    @Test
    public void testIsSolve(){
        questionService.isSolve(1);
    }

    /*
     *  根 据 问 题 id 设 置 为 未 解 决
     */
    @Test
    public void testNoSolve(){
        questionService.noSolve(1);
    }

    @Test
    public void testGetByPage(){
        Page<ClmsQuestion> page = new Page<>();
        // 当 前 页
        page.setCurrentPage(2);
        // 页 面 大 小
        page.setPageSize(3);
        // 总 页 数
        page.setTotalPage(0);
        // 总 数 据 条 数
        page.setTotalCount(0);
        // 排 序 列
        page.setSortColumn("update_time");
        // 排 序 方 式
        page.setSortMethod("asc");
        page = questionService.getByPage(page);
        System.out.println("当 前 页："+ page.getCurrentPage());
        System.out.println("总 页 数："+ page.getTotalPage());
        System.out.println("页 面 大 小："+ page.getPageSize());
        System.out.println("总 数 据 条 数："+ page.getTotalCount());
        System.out.println("起 始 索 引："+ page.getIndex());
        page.getList().forEach(question-> System.out.println(question));

    }
}

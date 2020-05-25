package com.tpg.cms.service;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.service.AnswerService;
import com.tpg.cms.utils.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class AnswerServiceTest {

    @Autowired
    private AnswerService answerService;


    /*
     *  添 加 数 据
     */
    @Test
    public void testSave(){
        ClmsAnswer clmsAnswer = new ClmsAnswer();
        // answer_id 自 动 增 长 不 需 要 传 参
        clmsAnswer.setQuestion_id(3);// 设 置 回 复 对 应 的 问 题 id
        clmsAnswer.setAnswer_author("测试人员"); // 设 置 答 复 作 者
        clmsAnswer.setAnswer_content("测试人员对第三个问题的第二个回答"); //设 置 答 复 内 容
        clmsAnswer.setAnswer_mark(0);
        // answer_good 默 认 值 为  0 不 需 要 传 参
        // answer_time 数 据 库 数 据 类 型 为 TIMESTAMP  默 认 值 为 CURRENT_TIMESTAMP 根 据 insert 时 间 自 动 保 存
        // answer_mark answer_status 默认值为 0
        answerService.save(clmsAnswer);
    }

    /*
    *  根 据 答 复 id 查 询 答 复 --- 未 删 除 的
    */
    @Test
    public void testGetById(){
        Integer id = 1;
        System.out.println(answerService.getById(id).toString());
    }

    /*
     *  根 据 答 复 id 查 询 答 复
     */
    @Test
    public void testGetDeletedById(){
        Integer id = 1;
        System.out.println(answerService.getNotDeletedById(id).toString());
    }

    /*
     *  根 据 答 复 id 删 除
     */
    @Test
    public void testDeletedById(){
        answerService.deleteById(8);
    }

    /*
     *  根 据 答 复 id 恢 复 已 删 除 的 数 据
     */
    @Test
    public void testRestoreById(){
        answerService.restoreById(8);
    }

    /*
     *  根 据 答 复 id 更 新 数 据
     */
    @Test
    public void testUpdate(){
        ClmsAnswer clmsAnswer = new ClmsAnswer();
        clmsAnswer.setAnswer_id(1); // 要 更 改 的 答 复 的 id
        clmsAnswer.setQuestion_id(2); // 设 置 对 应 的 问 题 id
        clmsAnswer.setAnswer_author("小赵"); // 设 置 答 复 人
        clmsAnswer.setAnswer_content("我看这个问题不简单，我应该解答不了"); //设 置 答 复 内 容
        clmsAnswer.setAnswer_mark(0); // 答 复 是 否 被 采 纳 ， 不 设 置 默 认 为 0 --- 未采纳
        answerService.update(clmsAnswer);
    }

    /*
     *  根 据 答 复 id 设 置 为 已 采 纳
     */
    @Test
    public void testAdopted(){
        answerService.Adopted(1);
    }

    /*
     *  根 据 答 复 id 设 置 为 未 采 纳
     */
    @Test
    public void testNotAdopted(){
        answerService.notAdopted(1);
    }


    /*
     *  分 页 查 询
     */
    @Test
    public void testGetByPage(){
        Page<ClmsAnswer> page = new Page<>();
        // 当 前 页
        page.setCurrentPage(2);
        // 页 面 大 小
        page.setPageSize(3);
        // 总 页 数
        page.setTotalPage(0);
        // 总 数 据 条 数
        page.setTotalCount(0);
        // 排 序 列
        page.setSortColumn("answer_time");
        // 排 序 方 式
        page.setSortMethod("asc");
        page = answerService.getByPage(page);
        System.out.println("当 前 页："+ page.getCurrentPage());
        System.out.println("总 页 数："+ page.getTotalPage());
        System.out.println("页 面 大 小："+ page.getPageSize());
        System.out.println("总 数 据 条 数："+ page.getTotalCount());
        System.out.println("起 始 索 引："+ page.getIndex());
        page.getList().forEach(answer-> System.out.println(answer));

    }
}

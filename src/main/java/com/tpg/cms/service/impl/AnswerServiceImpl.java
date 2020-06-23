package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsAnswerMapper;
import com.tpg.cms.dao.ClmsQuestionMapper;
import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.service.AnswerService;
import com.tpg.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private ClmsAnswerMapper answerMapper;

    @Autowired
    private ClmsQuestionMapper questionMapper;

    @Override
    public void save(ClmsAnswer clmsAnswer) {
        answerMapper.save(clmsAnswer); // 保存数据
        int qid = clmsAnswer.getQuestion_id();
        int aCount = questionMapper.getAnswerCount(qid);
        questionMapper.updateAnswerCount(qid, aCount); // 更新问题的答复数
    }

    // 根 据 id 删 除 数 据
    @Override
    public void deleteById(Integer id) {
        answerMapper.deletedById(id);
    }

    // 根 据 id 恢 复 已 删 除 的
    @Override
    public void restoreById(Integer id) {
        answerMapper.restoreById(id);
    }

    //更 新 数 据
    @Override
    public void update(ClmsAnswer clmsAnswer) {
        answerMapper.update(clmsAnswer);
    }

    @Override
    public void Adopted(Integer id) {
        int mark = 1;  // 设置答复状态码,已采纳
        answerMapper.changeAdopt(id, mark);
    }

    @Override
    public void notAdopted(Integer id) {
        int mark = 0; // 设置答复状态码,未采纳
        answerMapper.changeAdopt(id, mark);
    }

    // 根 据 id 查 询
    @Override
    public ClmsAnswer getById(Integer id) {
        return answerMapper.getById(id);
    }

    // 根 据 id 查 询 --- 查 询 未 删 除 的
    @Override
    public ClmsAnswer getNotDeletedById(Integer id) {
        return answerMapper.getNotDeletedById(id);
    }

    @Override
    public Page<ClmsAnswer> getByPage(Page<ClmsAnswer> page) {
        // 页 面 数 据
        List<ClmsAnswer> answerList = answerMapper.getByPage(page);
        page.setList(answerList);
        // 总 数 据 数
        int totalCount = answerMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    // 获 取 当 前 答 复 人 姓 名
//    @Override
//    public String getUserName() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
//    }
}

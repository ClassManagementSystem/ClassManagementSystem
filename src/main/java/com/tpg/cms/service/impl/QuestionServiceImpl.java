package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsQuestionMapper;
import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.service.QuestionService;
import com.tpg.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private ClmsQuestionMapper clmsQuestionMapper;

    @Override
    public void save(ClmsQuestion clmsQuestion) {
        clmsQuestionMapper.save(clmsQuestion);//保 存，新 增
    }

    @Override
    public void deleteById(Integer id) {
        clmsQuestionMapper.deletedById(id); // 根 据 id 删 除
    }

    @Override
    public void update(ClmsQuestion clmsQuestion) {
        clmsQuestionMapper.update(clmsQuestion); // 更 新 数 据
    }

    @Override
    public void isSolve(Integer id) {
        int mark = 1; //设置问题为已解决
        clmsQuestionMapper.changeSolve(id, mark);
    }

    @Override
    public void noSolve(Integer id) {
        int mark = 0; //设置问题为未解决
        clmsQuestionMapper.changeSolve(id, mark);
    }

    @Override
    public Page<ClmsQuestion> getByPage(Page<ClmsQuestion> page) {
        page.setList(clmsQuestionMapper.getByPage(page)); // 页 面 数 据
        page.setTotalCount(clmsQuestionMapper.getCountByPage(page)); // 总 数 据 数
        return page;
    }

    @Override
    public ClmsQuestion getById(Integer id) {
        return clmsQuestionMapper.getById(id); //根 据 id 查 询
    }
}

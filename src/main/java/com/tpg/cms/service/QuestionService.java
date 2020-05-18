package com.tpg.cms.service;

import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.utils.Page;

public interface QuestionService {

     // 保 存，新 增 数 据
    void save(ClmsQuestion clmsQuestion);

    //根 据 id 删 除
    void deleteById(Integer id);

    // 更 新 数 据
    void update(ClmsQuestion clmsQuestion);

    //设置问题为已解决
    void isSolve(Integer id);

    //设置问题为未解决
    void noSolve(Integer id);

    //分页查询
    Page<ClmsQuestion> getByPage(Page<ClmsQuestion> page);

    // 根 据 id 查 询
    ClmsQuestion getById(Integer id);
}

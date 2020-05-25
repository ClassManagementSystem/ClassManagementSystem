package com.tpg.cms.service;

import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.utils.Page;

public interface QuestionService {

     // 保 存，新 增 数 据
    void save(ClmsQuestion clmsQuestion);

    //根 据 id 删 除
    void deleteById(Integer id);

    // 根 据 id 恢 复 已 删 除 的
    void  restoreById(Integer id);

    // 更 新 数 据
    void update(ClmsQuestion clmsQuestion);

    // 设 置 问 题 为 已 解 决
    void isSolve(Integer id);

    // 设 置 问 题 为 未 解 决
    void noSolve(Integer id);

    // 分 页 查 询
    Page<ClmsQuestion> getByPage(Page<ClmsQuestion> page);

    // 根 据 id 查 询
    ClmsQuestion getById(Integer id);
}

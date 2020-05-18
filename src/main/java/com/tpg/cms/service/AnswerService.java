package com.tpg.cms.service;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.utils.Page;

public interface AnswerService {

    // 保 存 ，添 加 数 据
    void save(ClmsAnswer clmsAnswer);

    // 根 据 id 删 除
    void deleteById(Integer id);

    // 更 新 数 据
    void update(ClmsAnswer clmsAnswer);

    // 设 置 为 已 采 纳
    void Adopted(Integer id);

    // 设 置 为 未 采 纳
    void notAdopted(Integer id);

    // 根 据 id 查 询
    ClmsAnswer getById(Integer id);

    // 分 页 查 询
    Page<ClmsAnswer> getByPage(Page<ClmsAnswer> page);

    // 获 取 当 前 答 复 人 姓 名
    String getUserName();
}

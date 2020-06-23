package com.tpg.cms.service;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.utils.Page;

public interface ReportService {

    //根据报告id查询
    ClmsReport getById(Integer id);

    //通过用户id查询
    ClmsReport getByUserId(Integer user_id);

    // 分 页 查 询
    Page<ClmsReport> getByPage(Page<ClmsReport> page);

    // 设 置 为 已 批阅
    void isCheck(Integer id);

    // 设 置 为 未 批阅
    void noCheck(Integer id);
}

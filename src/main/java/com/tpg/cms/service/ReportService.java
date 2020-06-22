package com.tpg.cms.service;

import com.tpg.cms.model.ClmsReport;

public interface ReportService {

    //根据报告id查询
    ClmsReport getById(Integer id);

    //通过用户id查询
    ClmsReport getByUserId(Integer user_id);
}

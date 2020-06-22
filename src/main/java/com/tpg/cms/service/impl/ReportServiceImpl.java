package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsReportMapper;
import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ClmsReportMapper clmsReportMapper;

    @Override
    public ClmsReport getById(Integer id) {
        return clmsReportMapper.getById(id);
    }

    @Override
    public ClmsReport getByUserId(Integer user_id) {
        return clmsReportMapper.getByUserId(user_id);
    }
}

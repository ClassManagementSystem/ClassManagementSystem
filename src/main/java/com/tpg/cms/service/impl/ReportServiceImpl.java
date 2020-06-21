package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsReportMapper;
import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.service.ReportService;
import com.tpg.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Page<ClmsReport> getByPage(Page<ClmsReport> page) {
        // 页 面 数 据
        List<ClmsReport> reportList = clmsReportMapper.getByPage(page);
        page.setList(reportList);
        // 总 数 据 数
        int totalCount = clmsReportMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}

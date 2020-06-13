package com.tpg.cms.service;

import com.tpg.cms.dao.ClmsReportMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    //根据报告id查询
    @Test
    public void getByIdtest(){
        Integer id = 1;
        System.out.println(reportService.getById(id));
    }

    //根据用户id查询
    @Test
    public void getByUserId(){
        Integer user_id = 1;
        System.out.println(reportService.getByUserId(user_id));
    }


}

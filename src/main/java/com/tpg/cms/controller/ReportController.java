package com.tpg.cms.controller;

import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.service.ReportService;
import com.tpg.cms.utils.ResultAQ;
import com.tpg.cms.utils.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
@CrossOrigin
@Api(tags = "报告管理")
public class ReportController {

    @Autowired
    private ReportService reportService;

    //通过报告id查询
    @GetMapping("/get/{id}")
    public ResultAQ<ClmsReport> get(@PathVariable("id") Integer id){
//        ClmsReport clmsReport;
//        try{
//            clmsReport = reportService.getById(id);
//            System.out.println(clmsReport);
//        }catch (Exception e){
//            return new ResultAQ<>(ResultCode.ERROR, "系统错误，查询失败，请稍后再试！");
//        }
        return new ResultAQ<>(reportService.getById(id));
    }
}

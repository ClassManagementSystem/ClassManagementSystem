package com.tpg.cms.controller;

import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.service.ReportService;
import com.tpg.cms.utils.Page;
import com.tpg.cms.utils.ResultAQ;
import com.tpg.cms.utils.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    // 分 页 查 询
    @PostMapping("/getByPage")
    public ResultAQ<Page<ClmsReport>> getByPage(@RequestBody Page<ClmsReport> page){
        String sortColumn = page.getSortColumn();
        page.setSortColumn(sortColumn);
        page = reportService.getByPage(page);
        return new ResultAQ<>(page);
    }

    //设置为已批阅
    @PutMapping("/isCheck/{id}")
    public ResultAQ<Object> isCheck(@PathVariable Integer id){
        try{
            reportService.isCheck(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，修改失败，请稍后再试！");
        }
        return new ResultAQ<>("报告状态更新成功: 已批阅!");
    }

    //设置为未批阅
    @PutMapping("/noCheck/{id}")
    public ResultAQ<Object> noCheck(@PathVariable Integer id){
        try{
            reportService.noCheck(id);
        }catch (Exception e){
            return new ResultAQ<>(ResultCode.ERROR, "系统错误，修改失败，请稍后再试！");
        }
        return new ResultAQ<>("报告状态更新成功:未批阅!");
    }

}

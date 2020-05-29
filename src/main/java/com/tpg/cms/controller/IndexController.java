//package com.tpg.cms.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.tpg.cms.service.IndexService;
//import com.tpg.cms.utils.Result;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/admin/acl/index")
//@CrossOrigin
//@Api(tags = "登录管理")
//public class IndexController {
//
//    @Autowired
//    private IndexService indexService;
//
//    /**
//     * 根据token获取用户信息
//     */
//    @GetMapping("info")
//    public Result info(){
//        //获取当前登录用户用户名
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Map<String, Object> userInfo = indexService.getUserInfo(username);
//        return Result.OK().data(userInfo);
//    }
//
//    /**
//     * 获取菜单
//     * @return
//     */
//    @GetMapping("menu")
//    public Result getMenu(){
//        //获取当前登录用户用户名
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<JSONObject> permissionList = indexService.getMenu(username);
//        return Result.OK().data("permissionList", permissionList);
//    }
//
//    @PostMapping("logout")
//    public Result logout(){
//        return Result.OK();
//    }
//
//}

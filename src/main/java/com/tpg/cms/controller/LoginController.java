package com.tpg.cms.controller;

import com.tpg.cms.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nile
 */
@Api(tags = "用户登录管理")
@RestController
@RequestMapping("/clms")
@CrossOrigin //解决跨域
public class LoginController {

    //login
    @PostMapping("login")
    public Result login() {
        return Result.OK().data("token","admin");
    }
    //info
    @GetMapping("info")
    public Result info() {
        return Result.OK().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}

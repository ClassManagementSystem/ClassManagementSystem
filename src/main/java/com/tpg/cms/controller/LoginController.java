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
        return Result.OK().data("roles","[admin]").data("name","admin").data("avatar","https://images.pexels.com/photos/4319001/pexels-photo-4319001.jpeg?crop=entropy&cs=srgb&dl=man-in-white-t-shirt-and-brown-shorts-surfing-on-sea-waves-4319001.jpg&fit=crop&fm=jpg&h=800&w=640");
    }

}

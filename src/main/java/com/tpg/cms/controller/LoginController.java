package com.tpg.cms.controller;

import com.tpg.cms.config.security.jwt.JwtProvider;
import com.tpg.cms.model.request.LoginForm;
import com.tpg.cms.model.request.SignUpForm;
import com.tpg.cms.model.response.JwtResponse;
import com.tpg.cms.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Nile
 */
@Api(tags = "用户登录管理")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)  //解决跨域
public class LoginController {

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/signin")
    @ResponseBody
    public ResponseEntity<Object> callbackLogin(){
        return new ResponseEntity<>("未登录", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Object> csginup(@RequestBody SignUpForm signUpForm){
        //具体逻辑
        return new ResponseEntity<>("注册成功", HttpStatus.OK);
    }

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

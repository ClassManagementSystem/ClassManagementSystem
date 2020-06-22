package com.tpg.cms.controller;

import com.tpg.cms.model.ClmsUser;
import com.tpg.cms.model.SysRole;
import com.tpg.cms.model.SysUser;
import com.tpg.cms.security.jwt.JwtUtils;
import com.tpg.cms.service.UserService;
import com.tpg.cms.service.impl.UserServiceImpl;
import com.tpg.cms.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.jws.Oneway;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nile
 */
@Api(tags = "用户登录管理")
@RestController
@RequestMapping("/clms")
@CrossOrigin //解决跨域
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    //login
    @PostMapping("login")
    public Result login(@RequestBody SysUser sysUser) {

        //数据库查询，框架查询用户信息
        //校验用户名密码，成功没有？成功返回TOKEN，失败就返回登陆失败

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

//        String userDetails =  authentication.getPrincipal().toString();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());



        //SECURITY的配置里面，除了LOGIN，其他都必须带TOKEN，TOKEN用户名，数据库查是不是这个用户，

        return Result.OK().data("token",jwt);

//        return Result.OK().data("token","admin");
    }
    //info
    @GetMapping("info")
    public Result info() {
        return Result.OK().data("roles","Admin").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("register")
    public Result register(@RequestBody ClmsUser clmsUser) {
       userService.save(clmsUser);
       return Result.OK();

    }

}

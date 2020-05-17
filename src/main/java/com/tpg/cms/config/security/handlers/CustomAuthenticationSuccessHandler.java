package com.tpg.cms.config.security.handlers;


import com.alibaba.fastjson.JSON;
import com.tpg.cms.config.security.jwt.JwtProvider;
import com.tpg.cms.dao.ClmsUserMapper;
import com.tpg.cms.model.ClmsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*自定义 CustomAuthenticationSuccessHandler 类来实现 AuthenticationSuccessHandler 接口，用来处理认证成功后逻辑：
*onAuthenticationSuccess() 方法的第三个参数 Authentication 为认证后该用户的认证信息，这里打印日志后，重定向到了首页。
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ClmsUserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String jwtToken = jwtProvider.generateJwtToken(authentication);
        //ClmsUser user = userMapper.selectByUserName(authentication.getName());
        response.getWriter().write(JSON.toJSONString(jwtToken));
    }
}
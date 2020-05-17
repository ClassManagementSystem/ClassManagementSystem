package com.tpg.cms.config.security;

import com.tpg.cms.config.security.customauths.CustomUserDetailsService;
import com.tpg.cms.config.security.handlers.CustomAuthenticationFailureHandler;
import com.tpg.cms.config.security.handlers.CustomAuthenticationSuccessHandler;
import com.tpg.cms.config.security.jwt.JwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 设置密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置地址栏不能识别 // 的情况
     * @return
     */
    @Bean
    public DefaultHttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    /**
     * 注入jwt
     * @return
     */
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    /**
     * 自定义DaoAuthenticationProvider
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/login","/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                //设置登录方式,和登录接口，登录请求方式必须是Post
                .formLogin().loginPage("/login")
                // 自定义登录成功和失败处理
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                //把jwt加到security过滤器链中
                .addFilterBefore(authenticationJwtTokenFilter(), LogoutFilter.class)
                //禁止security自己创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 关闭CSRF跨域,开发和测试时较方便，上线时需要开启
        http.cors().and().csrf().disable();

    }

    @Override
    public void configure(WebSecurity web){
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**,/static/**","/templates/**","/img/**");
    }
}

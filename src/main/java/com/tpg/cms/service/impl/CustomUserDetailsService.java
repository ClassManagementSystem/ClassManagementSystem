package com.tpg.cms.service.impl;

import com.tpg.cms.dao.SysRoleMapper;
import com.tpg.cms.dao.SysUserMapper;
import com.tpg.cms.dao.SysUserRoleMapper;
import com.tpg.cms.model.SysRole;
import com.tpg.cms.model.SysUser;
import com.tpg.cms.model.SysUserRole;
import com.tpg.cms.service.SyRoleService;
import com.tpg.cms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserMapper usermapper;

    @Autowired
    private SysRoleMapper rolemapper;

    @Autowired
    private SysUserRoleMapper userrolemapper;

    //密码加密
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = usermapper.selectByUsername(username);

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<SysUserRole> userRoles = userrolemapper.selectByUserId(user.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = rolemapper.selectByPrimaryKey(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        // 返回UserDetails实现类
        return new SysUser(user.getUsername(), user.getPassword(), authorities);
    }
}
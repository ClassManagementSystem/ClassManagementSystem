package com.tpg.cms.config.security.customauths;


import com.tpg.cms.service.SysRoleService;
import com.tpg.cms.service.SysUserRoleService;
import com.tpg.cms.dao.ClmsUserMapper;
import com.tpg.cms.model.ClmsRole;
import com.tpg.cms.model.ClmsUser;
import com.tpg.cms.model.ClmsUserRole;
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
    private ClmsUserMapper userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        ClmsUser user = userService.selectByUserName(username);

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<ClmsUserRole> userRoles = userRoleService.listByUserId(user.getUserId());
        for (ClmsUserRole userRole : userRoles) {
            ClmsRole role = roleService.selectById(userRole.getRole_id());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new User(user.getUserName(), user.getUserPassword(), authorities);
    }
}
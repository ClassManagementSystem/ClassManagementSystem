//package com.tpg.cms.service.impl;
//
//import com.tpg.cms.config.security.entity.SecurityUser;
//import com.tpg.cms.config.security.entity.Users;
//import com.tpg.cms.dao.SysPermissionMapper;
//import com.tpg.cms.dao.SysUserMapper;
//import com.tpg.cms.model.SysUser;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
///**
// * <p>
// * 自定义userDetailsService - 认证用户详情
// * </p>
// */
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private SysUserMapper userMapper;
//
//    @Autowired
//    private SysPermissionMapper permissionMapper;
//
//    /***
//     * 根据账号获取用户信息
//     * @param username:
//     * @return: org.springframework.security.core.userdetails.UserDetails
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 从数据库中取出用户信息
//        SysUser user = userMapper.selectByUsername(username);
//
//        // 判断用户是否存在
//        if (null == user){
//            //throw new UsernameNotFoundException("用户名不存在！");
//        }
//        // 返回UserDetails实现类
//        Users curUser = new Users();
//        BeanUtils.copyProperties(user,curUser);
//
//        List<String> authorities = permissionMapper.selectPermissionValueByUserId(user.getId());
//        SecurityUser securityUser = new SecurityUser(curUser);
//        securityUser.setPermissionValueList(authorities);
//        return securityUser;
//    }
//
//}

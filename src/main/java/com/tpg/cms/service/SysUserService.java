package com.tpg.cms.service;

import com.github.pagehelper.PageInfo;
import com.tpg.cms.model.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface SysUserService {

    // 从数据库中取出用户信息
    SysUser selectByUsername(String username);

    PageInfo<SysUser> getByPage(Integer page, Integer limit, String username);

    SysUser getById(String id);

    int save(SysUser user);

    int updateById(SysUser user);

    int removeById(String id);

    int removeByIds(List<String> idList);
}

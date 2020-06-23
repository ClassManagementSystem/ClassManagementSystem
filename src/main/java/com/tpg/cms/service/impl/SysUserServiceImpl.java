package com.tpg.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpg.cms.dao.SysUserMapper;
import com.tpg.cms.model.SysUser;
import com.tpg.cms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;
    @Override
    public SysUser selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public PageInfo<SysUser> getByPage(Integer page, Integer limit, String username) {
        //用插件进行分页
        PageHelper.startPage(page, limit);
        List<SysUser> users = userMapper.selectList(username);
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public SysUser getById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(SysUser user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateById(SysUser user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int removeById(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int removeByIds(List<String> idList) {
        return userMapper.deleteByIds(idList);
    }
}

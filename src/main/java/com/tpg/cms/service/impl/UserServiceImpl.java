package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsUserMapper;
import com.tpg.cms.model.ClmsUser;
import com.tpg.cms.service.UserService;
import com.tpg.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @program: cms
 * @description: 用户服务层
 * @author: lx
 * @create: 2020-05-28 11:03
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ClmsUserMapper userMapper;

    /**
     * 添加新用户
     * @param clmsUser
     * @return
     */
    @Override
    public boolean save(ClmsUser clmsUser) {
        Date date1 = null;
        DateFormat df2 = null;
        String oldDate = clmsUser.getUser_birthday();
        try {
            oldDate= oldDate.replace("Z", " UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        clmsUser.setUser_birthday(df2.format(date1).toString());
        clmsUser.setStatus(0);
        return userMapper.save(clmsUser);
    }

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return userMapper.deletedById(id);
    }

    /**
     * 根据用户id获取用户
     * @param id
     * @return
     */
    @Override
    public ClmsUser getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public ClmsUser getByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean update(ClmsUser user) {
        return userMapper.update(user);
    }

    /**
     * 根据page参数分页查询用户信息
     * @param page
     * @return
     */
    public Page<ClmsUser> getByPage(Page<ClmsUser> page) {
        // 先查询数据
        List<ClmsUser> userList = userMapper.getByPage(page);
        page.setList(userList);

        // 在查询总数
        int totalCount = userMapper.getCountUser(page);
        page.setTotalCount(totalCount);
        return page;
    }
}

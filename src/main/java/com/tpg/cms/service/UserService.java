package com.tpg.cms.service;

import com.tpg.cms.model.ClmsUser;
import com.tpg.cms.utils.Page;

/**
 * 用户服务层
 */
public interface UserService {
    /**
     * 添加新用户
     * @param clmsUser
     * @return
     */
    boolean save(ClmsUser clmsUser);

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 根据用户id获取用户
     * @param id
     * @return
     */
    ClmsUser getById(Integer id);

    /**
     * 根据用户username获取用户
     * @param username
     * @return
     */
    ClmsUser getByUserName(String username);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean update(ClmsUser user);

    /**
     * 根据page参数分页查询用户信息
     * @param page
     * @return
     */
    Page<ClmsUser> getByPage(Page<ClmsUser> page);
}

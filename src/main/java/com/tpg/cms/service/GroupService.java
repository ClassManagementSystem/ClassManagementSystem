package com.tpg.cms.service;

import com.tpg.cms.dao.ClmsGroupMapper;
import com.tpg.cms.model.ClmsArticle;
import com.tpg.cms.model.ClmsGroup;
import com.tpg.cms.model.ClmsUser;
import com.tpg.cms.model.ClmsGroup;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

public interface GroupService {


    void save(ClmsGroup clmsGroup);

    // 根 据 id 删 除

    void deleteById(Integer id);

    // 根 据 id 查 询

    ClmsGroup getById(Integer id);

    // 更 新 数 据
    void update(ClmsGroup clmsGroup);

    // 分 页 查 询
    Page<ClmsGroup> getByPage(Page<ClmsGroup> page);

    List<ClmsGroup> getAll();

}

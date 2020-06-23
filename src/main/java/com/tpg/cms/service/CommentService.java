package com.tpg.cms.service;

import com.tpg.cms.model.ClmsComment;
import com.tpg.cms.utils.Page;

public interface CommentService {

    //根据id删除
    void deleteById(Integer id);

    //分页查询
    Page<ClmsComment> getByPage(Page<ClmsComment> page);
}

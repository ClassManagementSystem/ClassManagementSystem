package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsCommentMapper;
import com.tpg.cms.model.ClmsComment;
import com.tpg.cms.service.CommentService;
import com.tpg.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    ClmsCommentMapper clmsCommentMapper;

    @Override
    public void deleteById(Integer id) {
        clmsCommentMapper.deleteById(id);
    }

    @Override
    public Page<ClmsComment> getByPage(Page<ClmsComment> page) {
        // 页 面 数 据
        List<ClmsComment> commentList = clmsCommentMapper.getByPage(page);
        page.setList(commentList);
        // 总 数 据 数
        int totalCount = clmsCommentMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}

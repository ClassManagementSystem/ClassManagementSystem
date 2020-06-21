package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsGroupMapper;
import com.tpg.cms.model.ClmsGroup;
import com.tpg.cms.service. GroupService;
import com.tpg.cms.service.GroupService;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Mapper
@Repository
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    ClmsGroupMapper clmsGroupMapper;

    @Override
    public void save(ClmsGroup clmsGroup) {
        clmsGroupMapper.save(clmsGroup);
    }

    @Override
    public void deleteById(Integer id) {
        clmsGroupMapper.deleteById(id);
    }

    @Override
    public ClmsGroup getById(Integer id){
        return clmsGroupMapper.getById(id);
    }

    @Override
    public void update(ClmsGroup clmsGroup) { clmsGroupMapper.update(clmsGroup); }

    @Override
    public Page<ClmsGroup> getByPage(Page<ClmsGroup> page) {
        return null;
    }

}


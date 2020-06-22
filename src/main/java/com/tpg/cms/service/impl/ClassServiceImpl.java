package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsClassMapper;
import com.tpg.cms.model.ClmsArticle;
import com.tpg.cms.model.ClmsClass;
import com.tpg.cms.model.ClmsGroup;
import com.tpg.cms.service.ClassService;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class ClassServiceImpl implements ClassService {

        @Autowired
        ClmsClassMapper clmsClassMapper;

        @Override
        public void save(ClmsClass clmsClass) {
            clmsClassMapper.save(clmsClass);
        }

        @Override
        public void deleteById(Integer id) {
            clmsClassMapper.deleteById(id);
        }

        @Override
        public ClmsClass getById(Integer id){
            return clmsClassMapper.getById(id);
        }

        @Override
        public void update(ClmsClass clmsClass) {
            clmsClassMapper.update(clmsClass);
        }

        @Override
        public Page<ClmsClass> getByPage(Page<ClmsClass> page) {
            // 页 面 数 据
            List<ClmsClass> classList = clmsClassMapper.getByPage(page);
            page.setList(classList);
            // 总 数 据 数
            int totalCount = clmsClassMapper.getCountByPage(page);
            page.setTotalCount(totalCount);
            return page;
        }

    @Override
    public List<ClmsClass> getAll() {
        return clmsClassMapper.getAll();
    }
}



package com.tpg.cms.service;

import com.tpg.cms.model.ClmsClass;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClassService {
    //新增
    void save(ClmsClass clmsClass);

    // 根 据 id 删 除
    void deleteById(Integer id);

    // 根 据 id 查 询
    ClmsClass getById(Integer id);

    void update(ClmsClass clmsClass);

    Page<ClmsClass> getByPage(Page<ClmsClass> page);

    List<ClmsClass> getAll();
}

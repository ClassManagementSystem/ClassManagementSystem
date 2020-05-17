package com.tpg.cms.Service;


import com.tpg.cms.dao.ClmsRoleMapper;
import com.tpg.cms.model.ClmsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {
    @Autowired
    private ClmsRoleMapper roleMapper;

    public ClmsRole selectById(Integer id){
        return roleMapper.selectById(id);
    }

    public ClmsRole selectByName(String name) {
        return roleMapper.selectByName(name);
    }
}
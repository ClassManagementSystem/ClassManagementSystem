package com.tpg.cms.service;

import com.tpg.cms.model.ClmsClass;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ClassService {
    //新增
    @Insert("insert into clms_class(class_name) " +
            "values (#{class_name})")
    void save(ClmsClass clmsClass);

    // 根 据 id 删 除
    @Delete("delete from clms_class where class_id = #{id}")
    void deleteById(Integer id);

    // 根 据 id 查 询
    @Select("select * from clms_class where class_id = #{id}")
    ClmsClass getById(Integer id);

    // 更 新 数 据
    @Update("<script>" +
            "        update clms_class set class_id = #{class_id}\n" +
            "        <if test=\"class_name != null and class_name != ''\">\n" +
            "            ,class_name = #{class_name}\n" +
            "        </if>\n" +
            "        where class_id = #{class_id}" +
            "</script>")
    void update(ClmsClass clmsClass);

    Page<ClmsClass> getByPage(Page<ClmsClass> page);
}

package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsClass;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClmsClassMapper {
    // 新 增
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

    // 统 计 数 据 总 数
    @Select("<script>" +
            "        select count(*) from clms_class\n" +
            "        where class_id = class_id \n" +
            "        <if test=\"params.class_id!=null\">\n" +
            "            and class_id = #{params.class_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.class_name!=null\">\n" +
            "            and class_name like CONCAT('%', #{params.class_name}, '%')\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<ClmsClass> page);

    //分页查询
    @Select("<script>"+
            "       select class_id, class_name, (select count(*) from clms_user\n" +
            "        where clms_class.class_id = clms_user.user_class_id ) as class_count\n"+
            "        from clms_class\n" +
            "        where 1=1\n"+
            "        <if test=\"params.class_name!=null\">\n" +
            "            and class_name like CONCAT('%', #{params.class_name}, '%')\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}"+
            "</script>")
    List<ClmsClass> getByPage(Page<ClmsClass> page);

    @Select("select class_id, class_name, count(*) as class_count from clms_class, clms_user where clms_class.class_id = clms_user.user_class_id group by class_id")
    List<ClmsClass> getAll();
}
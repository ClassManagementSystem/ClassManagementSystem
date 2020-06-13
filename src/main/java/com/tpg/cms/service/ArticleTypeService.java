package com.tpg.cms.service;

import com.tpg.cms.model.ArticleStatistics;
import com.tpg.cms.model.ArticleType;
import com.tpg.cms.utils.Page;

import java.util.List;

public interface ArticleTypeService {

    //新增
    void save(ArticleType articleType);

    //根据id删除
    void deleteById(Integer id);

    //根据id查询
    ArticleType getById(Integer id);

    //更新数据
    void update(ArticleType articleType);

    //分页查询
    Page<ArticleType> getByPage(Page<ArticleType> page);

    //查询各类型对应的文章数以及占比
    Page<ArticleStatistics> getArticleTypeCountInfo(Page<ArticleStatistics> page);
}

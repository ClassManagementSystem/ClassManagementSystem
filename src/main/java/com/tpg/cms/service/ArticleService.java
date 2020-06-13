package com.tpg.cms.service;

import com.tpg.cms.model.ArticleStatistics;
import com.tpg.cms.model.ClmsArticle;
import com.tpg.cms.utils.Page;

public interface ArticleService {

    // 保 存 ，添 加 数 据
    void save(ClmsArticle clmsArticle);

    // 根 据 id 删 除
    void deleteById(Integer id);

    //根据id查询
    ClmsArticle getById(Integer id);

    //更新数据
    void update(ClmsArticle clmsArticle);

    // 分 页 查 询
    Page<ClmsArticle> getByPage(Page<ClmsArticle> page);

    //获取用户文章类型信息
    Page<ArticleStatistics> getArticleCountInfo(Page<ArticleStatistics> page);

    //查询用户文章排行
    Page<ArticleStatistics> getUserArticleCountInfo(Page<ArticleStatistics> page);
}

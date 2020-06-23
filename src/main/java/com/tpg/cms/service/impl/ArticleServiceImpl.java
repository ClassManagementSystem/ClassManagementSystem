package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ClmsArticleMapper;
import com.tpg.cms.model.ArticleStatistics;
import com.tpg.cms.model.ClmsArticle;
import com.tpg.cms.service.ArticleService;
import com.tpg.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ClmsArticleMapper clmsArticleMapper;

    @Override
    public void save(ClmsArticle clmsArticle) {
        clmsArticleMapper.save(clmsArticle);
    }

    @Override
    public void deleteById(Integer id) {
       clmsArticleMapper.deleteById(id);
    }

    @Override
    public ClmsArticle getById(Integer id){
       return clmsArticleMapper.getById(id);
    }

    @Override
    public void update(ClmsArticle clmsArticle) {
        clmsArticleMapper.update(clmsArticle);
    }

    @Override
    public Page<ClmsArticle> getByPage(Page<ClmsArticle> page) {
        // 页 面 数 据
        List<ClmsArticle> articleList = clmsArticleMapper.getByPage(page);
        page.setList(articleList);
        // 总 数 据 数
        int totalCount = clmsArticleMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public Page<ArticleStatistics> getArticleCountInfo(Page<ArticleStatistics> page) {
        // 先查询数据
        List<Map> maps = clmsArticleMapper.getArticleCountInfo(page);
        List<ArticleStatistics> asList = new ArrayList<>();
        for(Map m : maps){
            ArticleStatistics as = new ArticleStatistics();
            as.setName(m.get("user_name").toString());
            as.setArticle_count(Integer.parseInt(m.get("article_count").toString()));
            as.setLike_count(Integer.parseInt(m.get("like_count").toString()));
            as.setComment_count(Integer.parseInt(m.get("comment_count").toString()));
            asList.add(as);
        }
        page.setList(asList);
        // 再查询总数
        int totalCount = asList.size();
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public Page<ArticleStatistics> getUserArticleCountInfo(Page<ArticleStatistics> page) {
        // 先查询数据
        List<Map> maps = clmsArticleMapper.getUserArticleCountInfo(page);
        List<ArticleStatistics> asList = new ArrayList<>();
        for(Map m : maps){
            ArticleStatistics as = new ArticleStatistics();
            as.setName((String) m.get("type_name"));
            as.setArticle_count(Integer.valueOf(m.get("article_count").toString()).intValue());
            asList.add(as);
        }
        page.setList(asList);
        // 再查询总数
        int totalCount = asList.size();
        page.setTotalCount(totalCount);
        return page;
    }
}

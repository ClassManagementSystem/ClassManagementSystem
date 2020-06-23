package com.tpg.cms.service.impl;

import com.tpg.cms.dao.ArticleTypeMapper;
import com.tpg.cms.model.ArticleStatistics;
import com.tpg.cms.model.ArticleType;
import com.tpg.cms.service.ArticleTypeService;
import com.tpg.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Override
    public void save(ArticleType articleType) {
        articleTypeMapper.save(articleType);
    }

    @Override
    public void deleteById(Integer id) {
        articleTypeMapper.deleteById(id);
    }

    @Override
    public ArticleType getById(Integer id) {
        return articleTypeMapper.getById(id);
    }

    @Override
    public void update(ArticleType articleType) {
        articleTypeMapper.update(articleType);
    }

    @Override
    public Page<ArticleType> getByPage(Page<ArticleType> page) {
        // 页 面 数 据
        List<ArticleType> typeList = articleTypeMapper.getByPage(page);
        page.setList(typeList);
        // 总 数 据 数
        int totalCount = articleTypeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    public Page<ArticleStatistics> getArticleTypeCountInfo(Page<ArticleStatistics> page) {
        // 先查询数据
        List<Map> maps = articleTypeMapper.getArticleTypeCountInfo(page);
        List<ArticleStatistics> asList = new ArrayList<>();
        for(Map m : maps){
            ArticleStatistics as = new ArticleStatistics();
            as.setName((String) m.get("type_name"));
            as.setType_count(Integer.parseInt(m.get("article_count").toString()));
            // countMap.put("typeCounts", Integer.valueOf(m.get("").toString()).intValue());
            // as.setCountMap(countMap);
            asList.add(as);
            as.setPercent(Double.parseDouble(m.get("percent").toString()));
        }
        page.setList(asList);
        // 再查询总数
        int totalCount = asList.size();
        page.setTotalCount(totalCount);
        return page;
    }
}

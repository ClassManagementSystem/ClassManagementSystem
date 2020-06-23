package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleStatistics {

    //模型名称
    private String name;

    //类型数量
    private Integer type_count;

    //文章篇数
    private Integer article_count;

    //评论数
    private Integer comment_count;

    //获赞数
    private Integer like_count;

    //百分比
    private Double percent;
}

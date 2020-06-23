package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClmsArticle {
    private Integer article_id;

    private String article_title;

    private String article_content;

    private Integer article_author;

    private Integer article_like;

    private Integer article_comment;

    private Date create_time;

    private Date update_time;

    private Integer article_type;

    private String user_name;

    private String type_name;
    
}
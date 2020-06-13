package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClmsComment {

    private Integer comment_id;

    private Integer comment_user;

    private String comment_content;

    private Integer comment_like;

    private Date comment_time;

    private Integer comment_pid;

    private Integer article_id;

    private String user_name;

}
package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClmsLike {
    private Integer likeId;

    private Integer userId;

    private Integer articleId;

    private Integer commentId;

    private Date likeTime;
}
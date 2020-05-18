package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClmsQuestion {

    // 问 题 id
    private Integer question_id;

    //问 题 标 题
    private String question_title;

    // 问 题 内 容
    private String question_content;

    // 问 题 作 者
    private String question_author;

    // 提 问 时 间
    private String question_time;

    // 更新时间
    private String update_time;

    //点赞数量
    private Integer question_good;

    //答复数量
    private Integer answer_count;

    /**
     * 问题标记 可以理解为状态
     * 0 : 未解决
     * 1 : 已解决
     */
    private Integer question_mark;

    // 答 复 列 表 一 对 多
    private List<ClmsAnswer> answer_list;


    /**
     * 是否删除
     * 0 : 否
     * 1 : 是
     */
    private Integer question_status;


}

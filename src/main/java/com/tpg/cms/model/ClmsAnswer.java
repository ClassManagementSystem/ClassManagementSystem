package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
//在 返 回 前 端 的 时 候 忽 略 字 段 属 性 为 null 的 字 段，使 其 为 null 字 段 不 显 示
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClmsAnswer {

    //答 复 id
    private Integer answer_id;

    //问题id
    private Integer question_id;

    // 答复人
    private String answer_author;

    // 答复内容
    private String answer_content;

    //点赞数量
    private Integer answer_good;

   // 答复时间
    private String answer_time;

    //更新时间
    private String answer_update;

    /*
     * 答复标记
     * 0 : 已采纳
     * 1 : 未采纳
     */
    private Integer answer_mark;

    /*
     * 是否删除
     */
    private Integer answer_status;

}

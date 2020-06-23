package com.tpg.cms.model;

import lombok.Data;

import java.util.Date;

@Data
public class ClmsReport {
    private Integer report_id;

    private Integer report_type;

    private String report_content;

    private String report_difficulty;

    private String report_way;

    private String report_experience;

    private String report_nextplan;

    private Date create_time;

    private Date update_time;

    private Integer is_checked;

}
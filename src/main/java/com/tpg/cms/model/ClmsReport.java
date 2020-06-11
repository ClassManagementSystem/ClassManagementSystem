package com.tpg.cms.model;

import java.util.Date;

public class ClmsReport {
    private Integer reportId;

    private Integer reportType;

    private String reportContent;

    private String reportDifficulty;

    private String reportWay;

    private String reportExperience;

    private String reportNextplan;

    private Date createTime;

    private Date updateTime;

    private Integer isChecked;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent == null ? null : reportContent.trim();
    }

    public String getReportDifficulty() {
        return reportDifficulty;
    }

    public void setReportDifficulty(String reportDifficulty) {
        this.reportDifficulty = reportDifficulty == null ? null : reportDifficulty.trim();
    }

    public String getReportWay() {
        return reportWay;
    }

    public void setReportWay(String reportWay) {
        this.reportWay = reportWay == null ? null : reportWay.trim();
    }

    public String getReportExperience() {
        return reportExperience;
    }

    public void setReportExperience(String reportExperience) {
        this.reportExperience = reportExperience == null ? null : reportExperience.trim();
    }

    public String getReportNextplan() {
        return reportNextplan;
    }

    public void setReportNextplan(String reportNextplan) {
        this.reportNextplan = reportNextplan == null ? null : reportNextplan.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "ClmsReport{" +
                "reportId=" + reportId +
                ", reportType=" + reportType +
                ", reportContent='" + reportContent + '\'' +
                ", reportDifficulty='" + reportDifficulty + '\'' +
                ", reportWay='" + reportWay + '\'' +
                ", reportExperience='" + reportExperience + '\'' +
                ", reportNextplan='" + reportNextplan + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isChecked=" + isChecked +
                '}';
    }
}
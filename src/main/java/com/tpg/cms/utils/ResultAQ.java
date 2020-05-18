package com.tpg.cms.utils;

import lombok.Data;

@Data
public class ResultAQ<T>  {
    // code 返回状态码
    private Integer code;
    // msg 返回信息
    private String msg;
    // data 返回数据集
    private T data;


    public ResultAQ() {
        this.code = ResultCode.SUCCESS;
        this.msg = "操作成功";
    }
    public ResultAQ(String msg) {
        this.code = ResultCode.SUCCESS;
        this.msg = msg;
    }

    public ResultAQ(T data) {
        this.code = ResultCode.SUCCESS;
        this.msg = "操作成功";
        this.data = data;
    }
    public ResultAQ(String msg, T data) {
        this.code = ResultCode.SUCCESS;
        this.msg = msg;
        this.data = data;
    }
    public ResultAQ(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public ResultAQ(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

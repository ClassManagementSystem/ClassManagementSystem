/**
 * @FileName: Page
 * @Author: code-fusheng
 * @Date: 2020/3/21 13:17
 * Description: page 分页工具类
 */
package com.tpg.cms.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Page<T> {

    /**
     * asc 升序
     * desc 倒序
     */
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    // 当 前 页
    private Integer currentPage = 1;

    // 页 面 大 小（数据量）
    private Integer pageSize = 20;

   // 总 页 数
    private Integer totalPage = 0;

    // 总 数 据 量
    private Integer totalCount = 0;

    // 起 始 索 引
    private Integer index;

    // 返 回 给 前 端 的 数 据
    private List<T> list;

    // 条 件 参 数  初始化HashMap的容量为 16
    public Map<String, Object> params = new HashMap<>(16);

    // 排 序 列
    private String sortColumn;

    // 排 序 方 式
    private String sortMethod = "asc";

    // 获取当前页 当前当前页数小于1时，赋值为1
    public Integer getCurrentPage(){
        if(currentPage < 1){
            return 1;
        }
        return this.currentPage;
    }

    // 获 取 初 始 索 引
    public Integer getIndex(){
        return (currentPage - 1) * pageSize;
    }

     // 设 置 总 条 数   计 算 总 页 数
    public void setTotalCount(Integer totalCount){
        this.totalCount = totalCount;
        this.totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize); // 向 上 取 整
    }

    // 设 置 排 序 方 式
    public void setSortMethod(String sortMethod) {
        if ( sortMethod.isEmpty() ) {
            this.sortMethod = SORT_ASC; //如果为空 升序
        }
        if (sortMethod.toLowerCase().equals(SORT_ASC)) {
            this.sortMethod = SORT_ASC;
        } else if (sortMethod.toLowerCase().equals(SORT_DESC)) {
            this.sortMethod = SORT_DESC;
        } else {
            this.sortMethod = SORT_ASC; // 参 数 错 误 时 设 置 为 升 序
        }
    }

    // 设 置 输 出 数 据
    public void pagingDate(){
        int start = (currentPage-1) * pageSize;  //起始索引
        int end =  currentPage * pageSize; // 结 束 索 引
        if(currentPage <= totalPage && end >= totalCount){
            this.list = list.subList(start, totalCount); // 结 束 索 引 超 出 总 数 据 条 数
        }else if(currentPage > totalPage){
            this.currentPage = totalPage; // 当 前 页 码 超 出 总 页 数
            this.list = currentPage > 0 ? list.subList((currentPage-1) * pageSize, totalCount):null;
        }else {
            this. list = list.subList(start , end);
        }
    }
}

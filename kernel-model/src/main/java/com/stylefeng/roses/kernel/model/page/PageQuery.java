package com.stylefeng.roses.kernel.model.page;

import lombok.Data;

/**
 * 分页查询的请求参数封装
 *
 * @author fengshuonan
 * @date 2017-11-08-上午11:18
 */
@Data
public class PageQuery {

    /**
     * 每页的条数
     */
    private Integer pageSize;

    /**
     * 页编码(第几页)
     */
    private Integer pageNo;

    /**
     * 排序方式(asc 或者 desc)
     */
    private String sort;

    /**
     * 排序的字段名称
     */
    private String orderByField;

    public PageQuery() {
    }

    public PageQuery(Integer pageSize, Integer pageNo, String sort, String orderByField) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.sort = sort;
        this.orderByField = orderByField;
    }
}

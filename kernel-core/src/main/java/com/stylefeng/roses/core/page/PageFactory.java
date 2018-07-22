package com.stylefeng.roses.core.page;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.roses.core.util.HttpContext;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.kernel.model.page.PageQuery;

import javax.servlet.http.HttpServletRequest;

import static com.stylefeng.roses.core.util.ToolUtil.isNotEmpty;


/**
 * 默认分页参数构建
 *
 * @author fengshuonan
 * @date 2017年11月15日13:52:16
 */
public class PageFactory<T> {

    private static final String ASC = "asc";

    private static final String DESC = "desc";

    private static final String PAGE_SIZE = "pageSize";

    private static final String PAGE_NO = "pageNo";

    private static final String SORT_FIELD = "sort";

    private static final String ORDER = "order";

    public Page<T> defaultPage() {

        HttpServletRequest request = HttpContext.getRequest();
        int pageSize = 20;
        int pageNo = 1;

        //每页条数
        String pageSizeString = request.getParameter(PAGE_SIZE);
        if (isNotEmpty(pageSizeString)) {
            pageSize = Integer.valueOf(pageSizeString);
        }

        //第几页
        String pageNoString = request.getParameter(PAGE_NO);
        if (isNotEmpty(pageNoString)) {
            pageNo = Integer.valueOf(pageNoString);
        }

        //获取排序字段和排序类型(asc/desc)
        String sort = request.getParameter(SORT_FIELD);
        String order = request.getParameter(ORDER);

        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>(pageNo, pageSize);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>(pageNo, pageSize, sort);
            if (ASC.equalsIgnoreCase(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }

    public Page<T> createPage(PageQuery pageQuery) {

        int pageSize = 20;
        int pageNo = 1;

        if (pageQuery != null && isNotEmpty(pageQuery.getPageSize())) {
            pageSize = pageQuery.getPageSize();
        }

        if (pageQuery != null && isNotEmpty(pageQuery.getPageNo())) {
            pageNo = pageQuery.getPageNo();
        }

        if (pageQuery == null) {
            Page<T> page = new Page<>(pageNo, pageSize);
            page.setOpenSort(false);
            return page;
        } else {
            if (ToolUtil.isEmpty(pageQuery.getSort())) {
                Page<T> page = new Page<>(pageNo, pageSize);
                page.setOpenSort(false);
                return page;
            } else {
                Page<T> page = new Page<>(pageNo, pageSize, pageQuery.getSort());
                if (ASC.equalsIgnoreCase(pageQuery.getOrder())) {
                    page.setAsc(true);
                } else {
                    page.setAsc(false);
                }
                return page;
            }
        }
    }
}

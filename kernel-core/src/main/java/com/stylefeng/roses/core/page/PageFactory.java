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
public class PageFactory {

    /**
     * 排序，升序还是降序
     */
    private static final String ASC = "asc";

    /**
     * 每页大小的param名称
     */
    private static final String PAGE_SIZE_PARAM_NAME = "pageSize";

    /**
     * 第几页的param名称
     */
    private static final String PAGE_NO_PARAM_NAME = "pageNo";

    /**
     * 升序还是降序的param名称
     */
    private static final String SORT_PARAM_NAME = "sort";

    /**
     * 根据那个字段排序的param名称
     */
    private static final String ORDER_BY_PARAM_NAME = "orderBy";

    /**
     * 默认规则的分页
     *
     * @author fengshuonan
     * @Date 2018/7/23 下午4:11
     */
    public static <T> Page<T> defaultPage() {

        int pageSize = 20;
        int pageNo = 1;

        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            return new Page<>(pageNo, pageSize);
        } else {
            //每页条数
            String pageSizeString = request.getParameter(PAGE_SIZE_PARAM_NAME);
            if (isNotEmpty(pageSizeString)) {
                pageSize = Integer.valueOf(pageSizeString);
            }

            //第几页
            String pageNoString = request.getParameter(PAGE_NO_PARAM_NAME);
            if (isNotEmpty(pageNoString)) {
                pageNo = Integer.valueOf(pageNoString);
            }

            //获取排序字段和排序类型(asc/desc)
            String sort = request.getParameter(SORT_PARAM_NAME);
            String orderByField = request.getParameter(ORDER_BY_PARAM_NAME);

            if (ToolUtil.isEmpty(sort)) {
                Page<T> page = new Page<>(pageNo, pageSize);
                page.setOpenSort(false);
                return page;
            } else {
                Page<T> page = new Page<>(pageNo, pageSize, orderByField);
                if (ASC.equalsIgnoreCase(sort)) {
                    page.setAsc(true);
                } else {
                    page.setAsc(false);
                }
                return page;
            }
        }

    }

    /**
     * 自定义参数的分页
     *
     * @author fengshuonan
     * @Date 2018/7/23 下午4:11
     */
    public static <T> Page<T> createPage(PageQuery pageQuery) {

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
                Page<T> page = new Page<>(pageNo, pageSize, pageQuery.getOrderByField());
                if (ASC.equalsIgnoreCase(pageQuery.getSort())) {
                    page.setAsc(true);
                } else {
                    page.setAsc(false);
                }
                return page;
            }
        }
    }
}

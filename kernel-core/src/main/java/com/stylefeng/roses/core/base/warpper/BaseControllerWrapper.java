package com.stylefeng.roses.core.base.warpper;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.roses.kernel.model.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 控制器查询结果的包装类基类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:49:36
 */
public abstract class BaseControllerWrapper {

    public Map<String, Object> single = null;

    public List<Map<String, Object>> multi = null;

    public BaseControllerWrapper(Map<String, Object> single) {
        this.single = single;
    }

    public BaseControllerWrapper(List<Map<String, Object>> multi) {
        this.multi = multi;
    }

    public BaseControllerWrapper(Page<Map<String, Object>> page) {
        if (page != null && page.getRecords() != null) {
            this.multi = page.getRecords();
        }
    }

    public BaseControllerWrapper(PageResult<Map<String, Object>> pageResult) {
        if (pageResult != null && pageResult.getRows() != null) {
            this.multi = pageResult.getRows();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T wrap() {

        if (single != null) {
            wrapTheMap(single);
            return (T) single;
        }

        if (multi != null) {
            for (Map<String, Object> map : multi) {
                wrapTheMap(map);
            }
            return (T) multi;
        }

        return null;
    }

    protected abstract void wrapTheMap(Map<String, Object> map);
}

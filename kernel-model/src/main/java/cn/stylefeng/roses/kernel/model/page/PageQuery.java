/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.roses.kernel.model.page;

import cn.stylefeng.roses.core.reqres.request.RequestQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页查询的请求参数封装
 *
 * @author fengshuonan
 * @date 2017-11-08-上午11:18
 */
@Data
public class PageQuery extends RequestQuery {

    @ApiModelProperty(
            value = "每页的条数",
            example = "10"
    )
    private Integer pageSize;
    @ApiModelProperty(
            value = "页编码(第几页)",
            example = "1"
    )
    private Integer pageNo;
    @ApiModelProperty(
            value = "排序方式(asc 或者 desc)",
            example = "desc"
    )
    private String sort;
    @ApiModelProperty(
            value = "排序的字段名称",
            example = "id"
    )
    private String orderByField;

    public PageQuery() {
    }

    public PageQuery(Integer pageSize, Integer pageNo, String sort, String orderByField) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.sort = sort;
        this.orderByField = orderByField;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageQuery)) {
            return false;
        } else {
            PageQuery other = (PageQuery)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$pageSize = this.getPageSize();
                    Object other$pageSize = other.getPageSize();
                    if (this$pageSize == null) {
                        if (other$pageSize == null) {
                            break label59;
                        }
                    } else if (this$pageSize.equals(other$pageSize)) {
                        break label59;
                    }

                    return false;
                }

                Object this$pageNo = this.getPageNo();
                Object other$pageNo = other.getPageNo();
                if (this$pageNo == null) {
                    if (other$pageNo != null) {
                        return false;
                    }
                } else if (!this$pageNo.equals(other$pageNo)) {
                    return false;
                }

                Object this$sort = this.getSort();
                Object other$sort = other.getSort();
                if (this$sort == null) {
                    if (other$sort != null) {
                        return false;
                    }
                } else if (!this$sort.equals(other$sort)) {
                    return false;
                }

                Object this$orderByField = this.getOrderByField();
                Object other$orderByField = other.getOrderByField();
                if (this$orderByField == null) {
                    if (other$orderByField != null) {
                        return false;
                    }
                } else if (!this$orderByField.equals(other$orderByField)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageQuery;
    }


    public String toString() {
        return "PageQuery(pageSize=" + this.getPageSize() + ", pageNo=" + this.getPageNo() + ", sort=" + this.getSort() + ", orderByField=" + this.getOrderByField() + ")";
    }
}

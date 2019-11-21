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
package cn.stylefeng.roses.kernel.model.api.base;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 远程服务的参数的基类
 *
 * @author fengshuonan
 * @date 2018-08-06-下午4:22
 */
@Getter
@Setter
public abstract class AbstractBaseRequest implements BaseValidatingParam {

    @ApiModelProperty(
            hidden = true
    )
    private String requestNo;
    @ApiModelProperty(
            hidden = true
    )
    private String spanId;

    public AbstractBaseRequest() {
    }

    public String getRequestNo() {
        return this.requestNo;
    }

    public String getSpanId() {
        return this.spanId;
    }

    public void setRequestNo(final String requestNo) {
        this.requestNo = requestNo;
    }

    public void setSpanId(final String spanId) {
        this.spanId = spanId;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AbstractBaseRequest)) {
            return false;
        } else {
            AbstractBaseRequest other = (AbstractBaseRequest)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$requestNo = this.getRequestNo();
                Object other$requestNo = other.getRequestNo();
                if (this$requestNo == null) {
                    if (other$requestNo != null) {
                        return false;
                    }
                } else if (!this$requestNo.equals(other$requestNo)) {
                    return false;
                }

                Object this$spanId = this.getSpanId();
                Object other$spanId = other.getSpanId();
                if (this$spanId == null) {
                    if (other$spanId != null) {
                        return false;
                    }
                } else if (!this$spanId.equals(other$spanId)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AbstractBaseRequest;
    }


    public String toString() {
        return "AbstractBaseRequest(requestNo=" + this.getRequestNo() + ", spanId=" + this.getSpanId() + ")";
    }

}

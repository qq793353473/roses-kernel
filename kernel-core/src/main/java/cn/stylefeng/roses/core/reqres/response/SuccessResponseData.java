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
package cn.stylefeng.roses.core.reqres.response;

/**
 * 请求成功的返回
 *
 */
public class SuccessResponseData<T> extends ResponseData<T> {
    public SuccessResponseData() {
        super(true, DEFAULT_SUCCESS_CODE, "请求成功", null);
    }

    public SuccessResponseData(T t) {
        super(true, DEFAULT_SUCCESS_CODE, "请求成功", t);
    }

    public SuccessResponseData(Integer code, String message, T t) {
        super(true, code, message, t);
    }
}


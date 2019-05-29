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
package cn.stylefeng.roses.kernel.model.api;


import cn.stylefeng.roses.kernel.model.api.model.ReportResourceReq;
import cn.stylefeng.roses.kernel.model.api.model.ResourceUrlReq;
import cn.stylefeng.roses.kernel.model.api.model.UserResourceReq;
import cn.stylefeng.roses.kernel.model.resource.ResourceDefinition;

import java.util.Set;

/**
 * 系统管理服务端提供的远程服务
 *
 * @author fengshuonan
 * @date 2018-02-06 14:30
 */
public interface ResourceService {

    /**
     * 报告业务系统的资源(Resources)到服务器,appCode若重复则会覆盖
     */
    void reportResources(ReportResourceReq reportResourceReq);

    /**
     * 获取用户所拥有的资源url
     */
    Set<String> getUserResourceUrls(UserResourceReq userResourceReq);

    /**
     * 获取资源通过url
     */
    ResourceDefinition getResourceByUrl(ResourceUrlReq resourceUrlReq);
}

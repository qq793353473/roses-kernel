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
package cn.stylefeng.roses.core.reqres.request;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 响应结果封装
 *
 * @author fengshuonan
 * @Date 2018/2/11 23:04
 */
@Data
public class RequestData implements Serializable {

    private static final long serialVersionUID = 9081406366569775542L;

    @ApiModelProperty(
            hidden = true
    )
    private JSONObject data;
    @ApiModelProperty(
            hidden = true
    )
    private String ip;
    @ApiModelProperty(
            hidden = true
    )
    private String url;

    public <T> T parse(Class<T> clazz) {
        Map<String, Object> innerMap = this.data.getInnerMap();
        HashMap<String, Object> resultMap = new HashMap();
        Set<Map.Entry<String, Object>> entries = innerMap.entrySet();
        Iterator var5 = entries.iterator();

        while(var5.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var5.next();
            String key = (String)entry.getKey();
            String fieldName = StrUtil.toCamelCase(key);
            resultMap.put(fieldName, entry.getValue());
        }

        return BeanUtil.mapToBean(resultMap, clazz, true);
    }

    public <T> T parse(String key, Class<T> clazz) {
        return this.data.getObject(key, clazz);
    }

    public Object[] getObjectArray(String key) {
        JSONArray jsonArray = this.data.getJSONArray(key);
        return jsonArray != null ? jsonArray.toArray() : new Object[0];
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        JSONArray jsonArray = this.data.getJSONArray(key);
        return (List)(jsonArray != null ? jsonArray.toJavaList(clazz) : new ArrayList());
    }

    public <T> T[] getArray(String key, T[] array) {
        JSONArray jsonArray = this.data.getJSONArray(key);
        return jsonArray != null ? jsonArray.toArray(array) : array;
    }

    public Object get(String key) {
        return this.data.get(key);
    }

    public String getString(String key) {
        return this.data.getString(key);
    }

    public Integer getInteger(String key) {
        return this.data.getInteger(key);
    }

    public Long getLong(String key) {
        return this.data.getLong(key);
    }

    public Map<String, Object> parseMap() {
        return this.jsonObjet2Map(this.data);
    }

    private Map<String, Object> jsonObjet2Map(JSONObject jsonObj) {
        Map<String, Object> map = new HashMap();
        Set<Map.Entry<String, Object>> entries = jsonObj.getInnerMap().entrySet();
        Iterator<Map.Entry<String, Object>> itera = entries.iterator();
        Map.Entry<String, Object> entry = null;
        Object value = null;

        while(itera.hasNext()) {
            entry = (Map.Entry)itera.next();
            value = entry.getValue();
            map.put(entry.getKey(), this.traversalData(value));
        }

        return map;
    }

    private Object jsonArray2List(JSONArray array) {
        List<Object> list = new ArrayList();
        Iterator itera = array.iterator();

        while(itera.hasNext()) {
            Object value = itera.next();
            list.add(this.traversalData(value));
        }

        return list;
    }

    private Object traversalData(Object value) {
        if (value instanceof JSONObject) {
            return this.jsonObjet2Map((JSONObject)value);
        } else {
            return value instanceof JSONArray ? this.jsonArray2List((JSONArray)value) : value;
        }
    }

    public RequestData() {
    }

    public JSONObject getData() {
        return this.data;
    }

    public String getIp() {
        return this.ip;
    }

    public String getUrl() {
        return this.url;
    }

    public void setData(final JSONObject data) {
        this.data = data;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RequestData)) {
            return false;
        } else {
            RequestData other = (RequestData)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$data = this.getData();
                    Object other$data = other.getData();
                    if (this$data == null) {
                        if (other$data == null) {
                            break label47;
                        }
                    } else if (this$data.equals(other$data)) {
                        break label47;
                    }

                    return false;
                }

                Object this$ip = this.getIp();
                Object other$ip = other.getIp();
                if (this$ip == null) {
                    if (other$ip != null) {
                        return false;
                    }
                } else if (!this$ip.equals(other$ip)) {
                    return false;
                }

                Object this$url = this.getUrl();
                Object other$url = other.getUrl();
                if (this$url == null) {
                    if (other$url != null) {
                        return false;
                    }
                } else if (!this$url.equals(other$url)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RequestData;
    }


    public String toString() {
        return "RequestData(data=" + this.getData() + ", ip=" + this.getIp() + ", url=" + this.getUrl() + ")";
    }
}

package com.stylefeng.roses.kernel.model.enums;

import lombok.Getter;

/**
 * 是或者否的枚举
 *
 * @author stylefeng
 * @Date 2018/4/18 23:05
 */
@Getter
public enum YseOrNotEnum {

    Y(true, "是"),

    N(false, "否");

    private Boolean flag;
    private String desc;

    YseOrNotEnum(Boolean flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }

}

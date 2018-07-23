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

    Y("是"),

    N("否");

    private String desc;

    YseOrNotEnum(String desc) {
        this.desc = desc;
    }

}

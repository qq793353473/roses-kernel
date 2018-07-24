package com.stylefeng.roses.kernel.model.enums;

import lombok.Getter;

/**
 * 启用禁用标识
 *
 * @author fengshuonan
 * @Date 2018/7/24 下午5:31
 */
@Getter
public enum StatusEnum {

    ENABLE(1, "启用"),

    DISABLE(2, "禁用");

    private Integer code;
    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StatusEnum toEnum(Integer code) {
        if (null == code) {
            return StatusEnum.DISABLE;
        } else {
            for (StatusEnum e : StatusEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
            return StatusEnum.DISABLE;
        }
    }

}

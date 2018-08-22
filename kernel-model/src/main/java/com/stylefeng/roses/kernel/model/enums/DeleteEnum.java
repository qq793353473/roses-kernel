package com.stylefeng.roses.kernel.model.enums;

import com.stylefeng.roses.kernel.model.util.ValidateUtil;

/**
 * 是否删除的枚举
 *
 * @author fengshuonan
 * @Date 2018/1/15 10:17
 */
public enum DeleteEnum {

    DELETED("Y", "删除"),
    NOT_DELETED("N", "未删除");

    private String code;
    private String name;

    DeleteEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        if (ValidateUtil.isEmpty(code)) {
            return null;
        } else {
            for (DeleteEnum enumItem : DeleteEnum.values()) {
                if (enumItem.getCode().equals(code)) {
                    return enumItem.getName();
                }
            }
            return null;
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

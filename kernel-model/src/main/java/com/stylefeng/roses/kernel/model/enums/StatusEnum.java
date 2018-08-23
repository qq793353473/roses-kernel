package com.stylefeng.roses.kernel.model.enums;

/**
 * 状态的枚举
 *
 * @author fengshuonan
 * @Date 2018年1月23日 15:20:33
 */
public enum StatusEnum {

    ENABLE(1, "启用"),
    DISABLE(2, "禁用");

    private Integer code;
    private String name;

    StatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        if (code == null) {
            return "";
        } else {
            for (StatusEnum enumItem : StatusEnum.values()) {
                if (enumItem.getCode().equals(code)) {
                    return enumItem.getName();
                }
            }
            return "";
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

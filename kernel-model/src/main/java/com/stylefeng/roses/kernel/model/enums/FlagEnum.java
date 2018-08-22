package com.stylefeng.roses.kernel.model.enums;

/**
 * flag标识的枚举标识
 *
 * @author fengshuonan
 * @Date 2018/1/15 10:17
 */
public enum FlagEnum {

    Y("Y", true, "是"),
    N("N", false, "否");

    /**
     * 标识的编码,一般存在数据库中
     */
    private String code;

    /**
     * 标识的波尔值
     */
    private Boolean value;

    /**
     * 标识的描述
     */
    private String description;

    FlagEnum(String code, Boolean value, String description) {
        this.code = code;
        this.value = value;
        this.description = description;
    }

    /**
     * 通过值获取编码
     */
    public static String getCodeByValue(Boolean value) {
        for (FlagEnum flagEnum : FlagEnum.values()) {
            if (flagEnum.getValue().equals(value)) {
                return flagEnum.getCode();
            }
        }
        return "";
    }

    /**
     * 通过编码获取中文描述
     */
    public static String getDescriptionByCode(String code) {
        for (FlagEnum flagEnum : FlagEnum.values()) {
            if (flagEnum.getCode().equals(code)) {
                return flagEnum.getDescription();
            }
        }
        return "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

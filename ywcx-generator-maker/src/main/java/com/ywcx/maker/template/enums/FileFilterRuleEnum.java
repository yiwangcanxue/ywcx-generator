package com.ywcx.maker.template.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * 文件过滤规则枚举
 */
public enum FileFilterRuleEnum {
    CONTAINS("包含", "contains"),

    STARTS_WITH("前缀匹配", "startsWith"),

    ENDS_WITH("后缀匹配", "endsWith"),

    REGEX("正则", "regex"),


    EQUALS("相等", "equals");

    private final String name;
    private final String value;

    FileFilterRuleEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 根据value获取枚举
     *
     * @param value
     * @return
     */
    public static FileFilterRuleEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (FileFilterRuleEnum anEnum : FileFilterRuleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}

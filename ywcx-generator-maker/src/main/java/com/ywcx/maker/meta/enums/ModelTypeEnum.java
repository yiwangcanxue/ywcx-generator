package com.ywcx.maker.meta.enums;

/**
 * 模型类型枚举
 */
public enum ModelTypeEnum {
    STRING("字符串", "String"),

    BOOLEAN("布尔", "boolean");

    private final String name;
    private final String value;

    ModelTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

package com.ywcx.maker.meta.enums;

/**
 * 文件生成类型枚举
 */
public enum FileGenerateTypeEnum {
    DYNAMIC("动态", "dynamic"),

    STATIC("静态", "static");

    private final String name;
    private final String value;

    FileGenerateTypeEnum(String name, String value) {
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

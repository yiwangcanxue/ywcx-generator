package com.ywcx.maker.meta.enums;

/**
 * 文件类型枚举
 */
public enum FileTypeEnum {
    DIR("目录", "dir"),

    FILE("文件", "file"),

    GROUP("文件组", "group");

    private final String name;
    private final String value;

    FileTypeEnum(String name, String value) {
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

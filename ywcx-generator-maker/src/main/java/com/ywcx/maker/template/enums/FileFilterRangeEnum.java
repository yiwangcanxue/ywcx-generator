package com.ywcx.maker.template.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * 文件过滤范围枚举
 */
public enum FileFilterRangeEnum {
    FILE_NAME("文件名称", "fileName"),

    FILE_CONTENT("文件内容", "fileContent");


    private final String name;
    private final String value;

    FileFilterRangeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 根据value获取枚举
     *
     * @param value
     * @return
     */
    public static FileFilterRangeEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (FileFilterRangeEnum anEnum : FileFilterRangeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}

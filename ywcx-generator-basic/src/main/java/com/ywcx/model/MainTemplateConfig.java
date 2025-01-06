package com.ywcx.model;

import lombok.Data;

/**
 * 静态模板配置
 */
@Data
public class MainTemplateConfig {
    /**
     * 作者(字符串，填充值)
     */
    private String author = "ywcx";
    /**
     * 输出信息
     */
    private String outputText = "sum=";
    /**
     * 是否循环
     */
    private boolean loop;
}

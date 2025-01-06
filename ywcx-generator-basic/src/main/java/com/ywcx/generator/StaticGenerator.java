package com.ywcx.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 静态文件生成器
 */
public class StaticGenerator {
    
    /**
     * 拷贝文件（Hutool 实现，会将输入目录完整拷贝到输出目录下）
     *
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

}

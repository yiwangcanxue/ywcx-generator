package com.ywcx.generator;

import com.ywcx.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class MainGenerator {
    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        // 整个项目的根路径
        String projectPath = System.getProperty("user.dir");

        String parentPath = projectPath + File.separator + "ywcx-generator-basic";

        // 输入路径
        String inputPath = new File(projectPath, "ywcx-generator-demo-projects/acm-template").getAbsolutePath();

        String outputPath = projectPath;

        // 生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String inputDynamicFilePath = parentPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";

        String outputDynamicFilePath = projectPath + File.separator + "acm-template/src/com/ywcx/acm/MainTemplate.java";

        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("ywcx");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(mainTemplateConfig);
    }
}

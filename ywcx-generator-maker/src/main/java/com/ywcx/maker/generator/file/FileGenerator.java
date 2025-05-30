package com.ywcx.maker.generator.file;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class FileGenerator {
    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {

        String projectPath = System.getProperty("user.dir");

        // 整个项目的根路径
        File parentPath = new File(projectPath).getParentFile();

        // 输入路径
        String inputPath = new File(parentPath, "ywcx-generator-demo-projects/acm-template").getAbsolutePath();

        String outputPath = projectPath;

        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";

        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/ywcx/acm/MainTemplate.java";

        DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }

//    public static void main(String[] args) throws TemplateException, IOException {
//        DataModel dataModel = new DataModel();
//        dataModel.setAuthor("ywcx");
//        dataModel.setLoop(false);
//        dataModel.setOutputText("求和结果：");
//        doGenerate(dataModel);
//    }
}

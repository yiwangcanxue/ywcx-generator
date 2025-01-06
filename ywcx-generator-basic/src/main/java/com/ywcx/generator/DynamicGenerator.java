package com.ywcx.generator;

import com.ywcx.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态文件生成器
 */
public class DynamicGenerator {

    /**
     * 生成文件
     *
     * @param inputPath  模板文件输入路径
     * @param outputPath 模板文件输出路径
     * @param model      数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        String templateDir = new File(inputPath).getParent();

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File(templateDir));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        String templateName = new File(inputPath).getName();

        Template template = configuration.getTemplate(templateName);

        Writer out = new FileWriter(outputPath);

        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();

    }
}

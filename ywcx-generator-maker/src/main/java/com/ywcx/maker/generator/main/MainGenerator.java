package com.ywcx.maker.generator.main;


import cn.hutool.extra.template.TemplateException;


import java.io.IOException;

public class MainGenerator extends GenerateTemplate {

    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不要给我输出 dist 啦！");
    }

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException, freemarker.template.TemplateException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}

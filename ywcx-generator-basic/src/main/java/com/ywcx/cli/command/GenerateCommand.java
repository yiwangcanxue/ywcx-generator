package com.ywcx.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.ywcx.generator.MainGenerator;
import com.ywcx.model.MainTemplateConfig;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    /**
     * 作者(字符串，填充值)
     */
    @CommandLine.Option(names = {"-a", "--author"}, description = "作者", arity = "0..1", interactive = true, echo = true)
    private String author = "ywcx";
    /**
     * 输出信息
     */
    @CommandLine.Option(names = {"-o", "--outputText"}, description = "输出信息", arity = "0..1", interactive = true, echo = true)
    private String outputText = "sum=";
    /**
     * 是否循环
     */
    @CommandLine.Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", interactive = true, echo = true)
    private boolean loop;


    @Override
    public Integer call() throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }

}

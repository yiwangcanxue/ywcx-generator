package com.ywcx.maker.meta;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.ywcx.maker.meta.enums.FileGenerateTypeEnum;
import com.ywcx.maker.meta.enums.FileTypeEnum;
import com.ywcx.maker.meta.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MetaValidator {
    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);
        validAndFillFileConfig(meta);
        validAndFillModelConfig(meta);
    }

    private static void validAndFillModelConfig(Meta meta) {
        //modelConfig 校验和默认值
        Meta.ModelConfig mdoelConfig = meta.getModelConfig();
        if (mdoelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.ModelInfo> modelInfoList = mdoelConfig.getModels();
        if (!CollUtil.isEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
            //为group分组，不校验
            String groupKey = modelInfo.getGroupKey();
            if (StrUtil.isEmpty(groupKey)) {
                // 生成中间参数
                List<Meta.ModelConfig.ModelInfo> subModelInfoList = modelInfo.getModels();
                String allArgsStr = subModelInfoList.stream()
                        .map(subModelInfo ->
                                String.format("\"--%s\"", subModelInfo.getFieldName())
                        ).collect(Collectors.joining(","));
                modelInfo.setAllArgsStr(allArgsStr);
                continue;
            }

            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new MetaException("未填写 fieldName");
            }
            String modelInfoType = modelInfo.getType();
            if (StrUtil.isEmpty(modelInfoType)) {
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }
        }
    }

    private static void validAndFillFileConfig(Meta meta) {
        //fileConfig 校验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写 sourceRootPath");
        }
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source/" + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if (StrUtil.isEmpty(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputRootPath);
        }
        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputRootPath = "generated";
        if (StrUtil.isEmpty(outputRootPath)) {
            fileConfig.setOutputRootPath(defaultOutputRootPath);
        }
        String fileConfigType = fileConfig.getType();
        String defaultFileConfigType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isEmpty(fileConfigType)) {
            fileConfig.setType(defaultFileConfigType);
        }
        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (!CollUtil.isEmpty(fileInfoList)) {
            return;
        }
        for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
            String type = fileInfo.getType();
            if (FileTypeEnum.GROUP.getValue().equals(type)) {
                continue;
            }
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("未填写 inputPath");
            }
            String outputPath = fileInfo.getOutputPath();
            if (StrUtil.isEmpty(outputPath)) {
                fileInfo.setOutputPath(inputPath);
            }

            if (StrUtil.isBlank(type)) {
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    fileInfo.setType(FileTypeEnum.DIR.getValue());
                } else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());
                }
            }
            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isBlank(generateType)) {
                if (inputPath.endsWith(".ftl")) {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                } else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {
        //基础信息校验和默认值
        String name = StrUtil.blankToDefault(meta.getName(), "my-generator");
        meta.setName(name);

        String description = StrUtil.emptyToDefault(meta.getDescription(), "我的模板代码生成器");
        meta.setDescription(description);

        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.ywcx");
        meta.setDescription(basePackage);

        String version = StrUtil.emptyToDefault(meta.getVersion(), "1.0.0");
        meta.setVersion(version);

        String author = StrUtil.emptyToDefault(meta.getAuthor(), "ywcx");
        meta.setAuthor(author);

        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(), DateUtil.now());
        meta.setCreateTime(createTime);

    }
}

package com.ywcx.maker.template.model;

import com.ywcx.maker.meta.Meta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TemplateMakerFileConfig {
    private List<FileInfoConfig> files;

    private FileGroupConfig fileGroupConfig;

    @NoArgsConstructor
    @Data
    public static class FileInfoConfig {
        private String path;

        private String condition;

        private List<FileFilterConfig> filterConfigList;
    }

    @NoArgsConstructor
    @Data
    public static class FileGroupConfig {
        private String condition;
        private String groupKey;
        private String groupName;


    }
}

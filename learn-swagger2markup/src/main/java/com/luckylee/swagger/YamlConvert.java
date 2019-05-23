package com.luckylee.swagger;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * swagger.yaml convert to markdown
 *
 * @author Li Ning
 * @since 1.0
 */
public class YamlConvert {
    public static void main(String[] args) {
        Path localSwaggerFile = Paths.get("/Users/lining/workspace4intelliidea/JLearn/learn-swagger2markup/src/main/resources/swagger.yaml");
        Path outputFile = Paths.get("/Users/lining/workspace4intelliidea/JLearn/learn-swagger2markup/src/main/resources");

        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        Swagger2MarkupConverter converter = Swagger2MarkupConverter.from(localSwaggerFile)
                .withConfig(config)
                .build();
        converter.toFile(outputFile);
        converter.toFolder(outputFile);
    }
}



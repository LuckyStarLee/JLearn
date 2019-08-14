package com.luckylee;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.asciidoctor.Asciidoctor;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * swagger json convert to html.
 * <p>
 * <li>https://github.com/asciidoctor/asciidoctorj</li>
 * <li>https://github.com/rmpestano/cukedoctor</li>
 * ⭐⭐️⭐️️https://github.com/nitianziluli/swagger2pdf
 * </p>
 *
 * @author Li Ning
 * @since 1.0
 */
@Mojo(name = "swaggerJson", defaultPhase = LifecyclePhase.COMPILE)
public class SwaggerJsonConvertToHtmlMojo extends AbstractMojo {
    @Parameter(property = "swaggerJsonPath")
    private String sourceSwaggerJson;

    @Parameter(property = "remoteUrl")
    private String remoteUrl;

    @Parameter(property = "outPutPath")
    private String outPutPath;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(" generate html document start.");
        try {
            Path outputFile = Paths.get(outPutPath);

            Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                    .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                    .withOutputLanguage(Language.ZH)
                    .withPathsGroupedBy(GroupBy.TAGS)
                    .withGeneratedExamples()
                    .withoutInlineSchema()
                    .build();
            Swagger2MarkupConverter converter = null;
            if (sourceSwaggerJson != null && sourceSwaggerJson.length() > 0) {
                converter = Swagger2MarkupConverter.from(Paths.get(sourceSwaggerJson)).withConfig(config).build();
            }
            if (remoteUrl != null && remoteUrl.startsWith("http")) {
                URL apiFromUrl = new URL(remoteUrl);
                converter = Swagger2MarkupConverter.from(apiFromUrl)
                        .withConfig(config)
                        .build();

            }
            if (converter != null) {
                converter.toFile(outputFile);
                converter.toFolder(outputFile);
            }

        } catch (MalformedURLException e) {
            throw new MojoExecutionException("", e);
        }
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        getLog().info("generate html document end .");
    }
}


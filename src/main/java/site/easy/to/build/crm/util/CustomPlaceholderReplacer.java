package site.easy.to.build.crm.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class CustomPlaceholderReplacer {

    private final String databaseName;

    public CustomPlaceholderReplacer(String databaseName) {
        this.databaseName = databaseName;
    }

    public void executeScriptWithPlaceholderReplacement(String scriptPath) throws IOException {
        ClassPathResource resource = new ClassPathResource(scriptPath);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String scriptContent = new String(bytes, StandardCharsets.UTF_8);
        String replacedScriptContent = scriptContent.replace("@database.name@", databaseName);

        String outputFilePath = "src/main/resources/schema.sql";
        try (Writer writer = new FileWriter(outputFilePath)) {
            writer.write(replacedScriptContent);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java CustomPlaceholderReplacer <databaseName> <scriptPath>");
        }

        String databaseName = args[0];
        String scriptPath = args[1];

        CustomPlaceholderReplacer placeholderReplacer = new CustomPlaceholderReplacer(databaseName);
        placeholderReplacer.executeScriptWithPlaceholderReplacement(scriptPath);
    }
}

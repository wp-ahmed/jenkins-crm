package site.easy.to.build.crm.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
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
    }
}

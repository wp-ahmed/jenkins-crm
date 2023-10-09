package site.easy.to.build.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import site.easy.to.build.crm.util.CustomPlaceholderReplacer;

import java.io.IOException;

@SpringBootApplication
public class CrmApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CrmApplication.class);
	}

	public static void main(String[] args) throws IOException {

		String databaseName = System.getProperty("Database");
		CustomPlaceholderReplacer placeholderReplacer = new CustomPlaceholderReplacer(databaseName);
		placeholderReplacer.executeScriptWithPlaceholderReplacement("schema.sql");

		SpringApplication.run(CrmApplication.class, args);
	}
}

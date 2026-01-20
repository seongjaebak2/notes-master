package com.mysite.jwtdemo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

/**
 * Swagger UI 설정
 */
@Getter
@Setter
@Configuration
public class SwaggerConfiguration {

	private String appName = "JWT-DEMO";

	private String appDescription = "로그인/회원가입, 스프링시큐리티와 JWT 토큰 사용, JPA";

	private String appVersion = "1.0.0";

	private String contactName = "myName";

	private String contactUrl = "https://github.com/myName";

	private String contactMail = "myName@naver.com";

	@Bean
	public OpenAPI openAPI() {

		final Info apiInformation = getApiInformation();
		final Components components = new Components();

		final String schemeName = "bearerAuth";
		components.addSecuritySchemes(schemeName, new SecurityScheme().name(schemeName).type(HTTP).scheme("Bearer").bearerFormat("JWT"));

		final OpenAPI openAPI = new OpenAPI();
		openAPI.setInfo(apiInformation);
		openAPI.setComponents(components);
		openAPI.addSecurityItem(new SecurityRequirement().addList(schemeName));

		return openAPI;
	}

	private Info getApiInformation() {

		final Contact contact = new Contact();
		contact.setName(contactName);
		contact.setUrl(contactUrl);
		contact.setEmail(contactMail);

		final Info info = new Info();
		info.setTitle(appName);
		info.setVersion(appVersion);
		info.setDescription(appDescription);
		info.setContact(contact);

		return info;
	}

	@Bean
	GroupedOpenApi managementApi() {
		return GroupedOpenApi.builder().pathsToMatch("/actuator/**").group("Management Api").build();
	}

	@Bean
	GroupedOpenApi defaultApi() {
		return GroupedOpenApi.builder().pathsToExclude("/actuator/**").group("Default Api").build();
	}

}

package com.wsd.eCommerceBackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "JWT";
    private static final List<FilteredEndPoint> FILTERED_END_POINTS = Arrays.asList(
            new FilteredEndPoint(Pattern.compile("^.*/api/auth/signup$"), HttpMethod.POST),
            new FilteredEndPoint(Pattern.compile("^.*/api/auth/signin$"), HttpMethod.POST)
    );

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME, securityScheme()))
                .info(new Info()
                        .title("API doc")
                        .version("1.0.0")
                        .description("API Documentation for WSD e-commerce")
                )
                .security(Collections.singletonList(new SecurityRequirement().addList(SECURITY_SCHEME_NAME)));
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name(SECURITY_SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    private boolean filterProtectedOperations(Operation operation) {
        String urlPattern = String.valueOf(operation.getParameters().get(0).getContent().get("schema").getSchema().get$ref());
        HttpMethod httpMethod = HttpMethod.valueOf(operation.getOperationId().split("_")[0]);
        for (FilteredEndPoint filteredEndPoint : FILTERED_END_POINTS) {
            if (filteredEndPoint.pattern.matcher(urlPattern).matches() && filteredEndPoint.httpMethod == httpMethod) {
                return false;
            }
        }
        return true;
    }

    private static class FilteredEndPoint {
        Pattern pattern;
        HttpMethod httpMethod;

        FilteredEndPoint(Pattern pattern, HttpMethod httpMethod) {
            this.pattern = pattern;
            this.httpMethod = httpMethod;
        }
    }

}

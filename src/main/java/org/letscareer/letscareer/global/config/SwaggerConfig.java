package org.letscareer.letscareer.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.ExampleHolder;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.letscareer.letscareer.global.error.entity.ErrorResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    private final static String TOKEN_FORMAT = "JWT";
    private final static String TOKEN_TYPE = "bearer";
    private final static String SWAGGER_TITLE = "Lets career API";
    private final static String SWAGGER_VERSION = "1.0.0";

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title(SWAGGER_TITLE)
                .version(SWAGGER_VERSION);

        String jwtSchemeName = TOKEN_FORMAT;
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(jwtSchemeName);
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(TOKEN_TYPE)
                        .bearerFormat(TOKEN_FORMAT)
                );

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components)
                .info(info);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiErrorCode apiErrorCodeExamples = handlerMethod.getMethodAnnotation(ApiErrorCode.class);

            if (apiErrorCodeExamples != null) {
                generateErrorCodeResponseExample(operation, apiErrorCodeExamples.value());
            }

            return operation;
        };
    }

    // 여러 개의 에러 응답값 추가
    private void generateErrorCodeResponseExample(Operation operation, SwaggerEnum[] errorCodes) {
        ApiResponses responses = operation.getResponses();

        // ExampleHolder(에러 응답값) 객체를 만들고 에러 코드별로 그룹화
        Map<Integer, List<ExampleHolder>> statusWithExampleHolders = Arrays.stream(errorCodes)
                .map(errorCode -> ExampleHolder.builder()
                        .holder(getSwaggerExample(errorCode.getErrorCode()))
                        .code(errorCode.getErrorCode().getHttpStatus().value())
                        .name(errorCode.getErrorCode().getMessage())
                        .build()
                )
                .collect(Collectors.groupingBy(ExampleHolder::getCode));

        addExamplesToResponses(responses, statusWithExampleHolders);
    }

    // ErrorResponseDto 형태의 예시 객체 생성
    private Example getSwaggerExample(ErrorCode errorCode) {
        ErrorResponse errorResponseDto = ErrorResponse.of(errorCode);
        Example example = new Example();
        example.setValue(errorResponseDto);

        return example;
    }

    // exampleHolder를 ApiResponses에 추가
    private void addExamplesToResponses(ApiResponses responses,
                                        Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach(
                (status, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();

                    v.forEach(
                            exampleHolder -> mediaType.addExamples(
                                    exampleHolder.getName(),
                                    exampleHolder.getHolder()
                            )
                    );
                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    responses.addApiResponse(String.valueOf(status), apiResponse);
                }
        );
    }
}

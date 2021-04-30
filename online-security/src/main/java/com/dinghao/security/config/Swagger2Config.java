//package com.dinghao.security.config;
//
//import com.dinghao.security.entity.SwaggerProperties;
//
//import org.springframework.boot.SpringBootVersion;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//
//import java.util.*;
//
//@Configuration
//@EnableSwagger2
//@ComponentScan("com.dinghao.security.config")
//public class Swagger2Config implements WebMvcConfigurer {
//
//    private final SwaggerProperties swaggerProperties;
//
//    public Swagger2Config(SwaggerProperties swaggerProperties) {
//        this.swaggerProperties = swaggerProperties;
//    }
//
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(true)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.dinghao.security.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
//    }
//
//    /**
//     * 设置授权信息
//     */
//    private List<SecurityScheme> securitySchemes() {
//        ApiKey apiKey = new ApiKey("Authorization", "token", "header");
//        return Collections.singletonList(apiKey);
//    }
//
//    /**
//     * 授权信息全局应用
//     */
//    private List<SecurityContext> securityContexts() {
//        List<SecurityContext> securityContexts=new ArrayList<>();
//        securityContexts.add(
//                SecurityContext.builder()
//                        .securityReferences(defaultAuth())
//                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
//                        .build());
//        return securityContexts;
//
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        List<SecurityReference> securityReferences=new ArrayList<>();
//        securityReferences.add(new SecurityReference("token", authorizationScopes));
//        return securityReferences;
//    }
//
//
//
//
//
//    /**
//     * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
//     */
////    @SuppressWarnings("unchecked")
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        try {
////            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
////            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
////            if (registrations != null) {
////                for (InterceptorRegistration interceptorRegistration : registrations) {
////                    interceptorRegistration
////                            .excludePathPatterns("/swagger**/**")
////                            .excludePathPatterns("/webjars/**")
////                            .excludePathPatterns("/v3/**")
////                            .excludePathPatterns("/doc.html");
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title(swaggerProperties.getApplicationName() + "Restful APIs")//网页标题
//                .description(swaggerProperties.getApplicationDescription())//网页描述
//                .termsOfServiceUrl("http://localhost:9090/")//接口访问地址
//                .contact(new Contact("user",null,"******@qq.com"))//名称，url，邮箱
//                .version("Application Version: " + swaggerProperties.getApplicationVersion() + ", Spring Boot Version: " + SpringBootVersion.getVersion())//接口版本号
//                .build();
//
//    }
//}
package com.kiramie.swagger.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author yangbin
 * @since 2022/11/22
 **/
//@Configuration
//@EnableSwagger2WebMvc
//@Profile(value = {"test", "dev"})
public class SwaggerConfigDemo {
    @Value("${spring.profiles.active}")
    private String env;

    @Bean(value = "governmentApi")
    public Docket governmentApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        if (!"dev".equals(env)) {
            docket.host("/api");
        }

        docket.apiInfo(apiInfo("蜂擎云服-政企版", "蜂擎云服-政企版接口文档(政府端)"))
                .groupName("政府端")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gongsibao.controller.government"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    @Bean(value = "companyApi")
    public Docket companyApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        if (!"dev".equals(env)) {
            docket.host("/api");
        }

        docket.apiInfo(apiInfo("蜂擎云服-政企版", "蜂擎云服-政企版接口文档(企业端)"))
                .groupName("企业端")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gongsibao.controller.company"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean(value = "commonApi")
    public Docket commonApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        if (!"dev".equals(env)) {
            docket.host("/api");
        }

        docket.apiInfo(apiInfo("蜂擎云服-政企版", "蜂擎云服-政企版接口文档(公共接口)"))
                .groupName("公共")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gongsibao.controller.common"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    /**
     * ---------------------------------------------------------------------------------------------------------
     * 接口文档详细信息
     *
     * @return
     */
    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version("1.0.0")
                .build();
    }
}

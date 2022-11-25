package com.kiramie.authority.config;

import lombok.extern.slf4j.Slf4j;
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

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author yangbin
 * @since 2022/11/22
 **/
@Configuration
@EnableSwagger2WebMvc
@Profile(value = {"test", "dev"})
@Slf4j
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String env;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String port;

    @Bean(value = "governmentApi")
    public Docket governmentApi() throws UnknownHostException {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket.apiInfo(apiInfo("krm权限服务", "krm权限服务"))
                .groupName("测试接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kiramie.authority.controller.test"))
                .paths(PathSelectors.any())
                .build();
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        log.info("{}环境应用{}启动成功!swagger地址：http://{}:{}/doc.html", env, appName, hostAddress, port);
        return docket;
    }

    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version("1.0.0")
                .build();
    }
}

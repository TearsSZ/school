package com.dlb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author tears
 * @date 2022/1/8
 * /*
 * 类注解Api(tags = "登录模块")
 * 方法注解ApiOperation("此方法XXX")
 * //访问3.0网址:http://localhost/swagger-ui/index.html
 * //2.0的:http://localhost/swagger-ui.html
 */
@Configuration
//@EnableSwagger2 3.0之前版本用
@EnableOpenApi
public class Swagger2Config {
    @Bean
    public Docket docket(Environment environment){
        //设置要显示的Swagger的环境  当前是dev
        Profiles profiles = Profiles.of("dev");
        //获取环境 判断现在是什么环境  如果当前的环境和profiles 一样为TRUE
        boolean b = environment.acceptsProfiles(profiles);
        System.out.println("目标环境和使用环境对比结果为:" + b);

        return new Docket(DocumentationType.OAS_30)
                //.apiInfo(new ApiInfoBuilder().title("众筹项目").build());
                .apiInfo(apiInfo())
                .groupName("大萝卜组")//组名
                .enable(b)//是否启动丝袜哥
                .select()
                //any() 扫描全部
                //none() 不扫描
                //basePackage() 扫描指定包
                //withClassAnnotation() 扫描类上的注解 参数是一个注解的反射对象
                //withMethodAnnotation(GetMapping.class)
                .apis(RequestHandlerSelectors.basePackage("com.dlb.controller"))
                //扫描过滤 ,只扫描 ant("请求")
                //.paths(PathSelectors.ant("/a/*"))
                .build();

    }
    //配置swagger信息 需要apiInfo()
    public ApiInfo apiInfo() {
        Contact contact = new Contact("大萝北北", "http://www.gonogo.com", "279080122@qq.com");
        return new ApiInfo("校园通"
                , "本项目由大萝卜打造，使用springboot框架搭建。向着小目标前进"
                , "1.0"
                , "urn:tos"
                , contact
                , "萝北贝的博客"
                , "https://www.cnblogs.com/9080dlb/"
                , new ArrayList());
    }
}















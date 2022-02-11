package com.dlb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tears
 * @date 2022/2/11
 */
@Configuration
@PropertySource("classpath:allocation.properties") //读取配置文件allocation
@ConfigurationProperties(prefix="oss") //读取oss节点
@Data //使用set方法将oss节点中的值填充到当前类的属性中
//打开项目设置选择模块--spring---点击spring开关按钮--点击加号找到目标文件，
//此时配置文件是全黄色，重启项目或者重新构建项目就OK了，可以点击跳转！记得不要忘记导包
public class OSSConfig {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String fileDir;
}

package com.dlb;

import com.aliyun.oss.OSSClient;
import com.dlb.config.OSSConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SchoolApplicationTests {

    @Autowired
    private OSSConfig ossConfig;


    @Test
    void contextLoads() {
        System.out.println(ossConfig.getAccessKeyId());
        System.out.println("akid-----------");


    }

}

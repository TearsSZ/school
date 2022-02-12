package com.dlb;

import com.dlb.utils.OSSClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolApplicationTests {



    @Autowired
    private OSSClientUtil ossClientUtil;


    //http://www.vmfor.com/p/101358283368.html
    //各种查询方法


    @Test
    void contextLoads() {
        System.out.println(ossClientUtil.getAccessKeyId());
        System.out.println("akid-----------");
        ossClientUtil.init();
      //  System.out.println("-----======"+a);


    }

}

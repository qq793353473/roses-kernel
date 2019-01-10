package cn.stylefeng.roses.core.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = WebAutoConfiguration.class
)
public class WebAutoConfigurationNoWebTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testNoWebEnvironment() throws Exception {
        // 本测试指定了 webEnvironment = SpringBootTest.WebEnvironment.NONE，
        // 在这个情况下 WebAutoConfiguration 应该自动跳过初始化，而不是报错。
        Assert.assertFalse(applicationContext.containsBean("error"));
    }
}
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
        classes = WebAutoConfiguration.class
)
public class WebAutoConfigurationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testWebEnvironment() throws Exception {
        // 在这个情况下 WebAutoConfiguration 应该正常初始化。
        Assert.assertTrue(applicationContext.containsBean("error"));
    }
}
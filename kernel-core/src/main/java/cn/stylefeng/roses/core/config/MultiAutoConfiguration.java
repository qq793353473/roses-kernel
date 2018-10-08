package cn.stylefeng.roses.core.config;

import cn.stylefeng.roses.core.mutidatasource.aop.MultiSourceExAop;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 多数据源配置
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@ConditionalOnProperty(prefix = "roses", name = "muti-datasource-open", havingValue = "true")
public class MultiAutoConfiguration {

    @Bean
    public MultiSourceExAop multiSourceExAop() {
        return new MultiSourceExAop();
    }
}

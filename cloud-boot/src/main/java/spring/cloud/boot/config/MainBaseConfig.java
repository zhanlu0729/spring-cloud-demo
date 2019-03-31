package spring.cloud.boot.config;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ComponentScan(basePackages = {MainBaseConfig.BASE_PACKAGES})
@Configuration
public class MainBaseConfig {

    /**
     * 扫描的基础包
     */
    public static final String BASE_PACKAGES = "com.spring.cloud";

    /**
     * 启用Feign的基础配置
     */
    @Configuration
    @EnableFeignClients(basePackages = {MainBaseConfig.BASE_PACKAGES})
    @ConditionalOnClass(FeignClient.class)
    static class BoneFeignClientConfig {

    }

    /**
     * 启用【Security】的基础配置
     */
    @Configuration
    @EnableWebSecurity
    @ConditionalOnClass(WebSecurityConfiguration.class)
    static class BoneWebSecurityConfig extends WebSecurityConfigurerAdapter {

        /**
         * 禁用端点的安全校验
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            super.configure(http);
        }
    }

    /**
     * 启用熔断器的基础配置
     */
    @Configuration
    @EnableCircuitBreaker
    @ConditionalOnClass(HystrixCommand.class)
    static class BoneCircuitBreakerConfig {

    }

}

package com.bone.cloud.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Boot应用统一入口
 *
 * @author 杨新伦
 * @date 2019-03-01
 */
@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Main.run(Main.class, args);
    }

    /**
     * 应用入口方法
     *
     * @param clazz 注有@SpringBootApplication注解的类
     * @param args 参数列表
     * @return 应用启动结果
     */
    public static ConfigurableApplicationContext run(Class<?> clazz, String[] args) {
        ConfigurableApplicationContext ctx = null;
        try {
            ctx = new SpringApplication(clazz).run(args);
        } catch (Exception e) {
            log.error("应用启动失败", e);
        }
        return ctx;
    }

}

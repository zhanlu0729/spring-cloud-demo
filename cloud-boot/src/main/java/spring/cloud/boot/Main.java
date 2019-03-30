package spring.cloud.boot;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * Spring Boot应用统一入口
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
            //创建app
            SpringApplication app = new SpringApplication(clazz);
            //关闭banner
            app.setBannerMode(Mode.OFF);
            //创建Spring上下文
            List<String> argList = Stream.of(getEnv(args), getAppName(args)).collect(Collectors.toList());
            ctx = app.run(argList.toArray(new String[argList.size()]));
        } catch (Exception e) {
            log.error("应用启动失败", e);
        }
        return ctx;
    }

    //从环境变量获取应用所在环境
    private static String getEnv(String[] args) {
        //从环境变量获取应用所在环境
        String env = System.getenv("ENV");
        if (StringUtils.hasText(env) && !env.startsWith("--env=")) {
            env = "--env=" + env;
            log.info("ENV={}", env);
        }
        //从命令行获取应用所在环境
        if (!StringUtils.hasText(env) && args != null && args.length > 0) {
            for (String arg : args) {
                if (arg != null && arg.startsWith("--env=")) {
                    env = arg;
                    break;
                }
            }
            log.info("cmd_line_env={}", env);
        }
        if (!StringUtils.hasText(env)) {
            env = "--env=local";
        }
        log.info("env={}", env);
        return env;
    }

    //从环境变量获取应用英文名
    private static String getAppName(String[] args) {
        //从环境变量获取应用英文名
        String appName = System.getenv("SPRING_APPLICATION_NAME");
        if (StringUtils.hasText(appName) && !appName.startsWith("--spring.application.name=")) {
            appName = "--spring.application.name=" + appName;
            log.info("SPRING_APPLICATION_NAME={}", appName);
        }
        //从命令行获取应用英文名
        if (!StringUtils.hasText(appName) && args != null && args.length > 0) {
            for (String arg : args) {
                if (arg != null && arg.startsWith("--spring.application.name=")) {
                    appName = arg;
                    break;
                }
            }
            log.info("cmd_line_appName={}", appName);
        }
        if (!StringUtils.hasText(appName)) {
            throw new NullPointerException("[--spring.application.name=appName]参数不能空");
        }
        log.info("spring.application.name={}", appName);
        return appName;
    }

}

package spring.cloud.boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainTest {

    public static void main(String[] args) {
        args = new String[]{"--spring.application.name=config-server"};
        Main.run(MainTest.class, args);
    }

}

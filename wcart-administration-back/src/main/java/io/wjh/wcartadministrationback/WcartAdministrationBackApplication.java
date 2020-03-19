package io.wjh.wcartadministrationback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("io.wjh.wcartadministrationback.dao")
@EnableAsync
public class WcartAdministrationBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcartAdministrationBackApplication.class, args);
    }

}

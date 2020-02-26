package io.wjh.wcartadministrationback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.wjh.wcartadministrationback.dao")
public class WcartAdministrationBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcartAdministrationBackApplication.class, args);
    }

}

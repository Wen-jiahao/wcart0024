package io.wjh.wcartstoreback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.wjh.wcartstoreback.dao")
public class WcartStoreBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcartStoreBackApplication.class, args);
    }

}

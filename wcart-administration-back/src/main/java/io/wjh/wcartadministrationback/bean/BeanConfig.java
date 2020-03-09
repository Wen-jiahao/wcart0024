package io.wjh.wcartadministrationback.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import sun.security.provider.SecureRandom;

@Configuration
public class BeanConfig {

    @Bean
    public SecureRandom secureRandom(){
        return new SecureRandom();
    }

}

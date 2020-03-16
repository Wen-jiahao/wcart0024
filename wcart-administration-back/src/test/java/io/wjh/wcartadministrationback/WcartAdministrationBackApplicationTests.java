package io.wjh.wcartadministrationback;

import io.wjh.wcartadministrationback.po.Administrator;
import io.wjh.wcartadministrationback.service.AdministratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WcartAdministrationBackApplicationTests {

    @Autowired
    private AdministratorService administratorService;
    @Test
    void contextLoads() {
        String username="wjh";
        Administrator administratorServiceByUsername = administratorService.getByUsername(username);
        assertNotNull(administratorServiceByUsername);
        String username1="wjh1";
        Administrator administratorServiceByUsername1 = administratorService.getByUsername(username1);
        assertNotNull(administratorServiceByUsername1);
    }

}

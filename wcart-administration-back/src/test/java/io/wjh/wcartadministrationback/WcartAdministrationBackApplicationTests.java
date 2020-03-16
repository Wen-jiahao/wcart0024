package io.wjh.wcartadministrationback;

import io.wjh.wcartadministrationback.constant.ClientExceptionConstant;
import io.wjh.wcartadministrationback.controller.AdministratorController;
import io.wjh.wcartadministrationback.dto.in.AdministratorLoginInDTO;
import io.wjh.wcartadministrationback.dto.out.AdministratorLoginOutDTO;
import io.wjh.wcartadministrationback.exception.ClientException;
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
    @Autowired
    private AdministratorController administratorController;
    @Test
    void contextLoads() {
        String username="wjh";
        Administrator administratorServiceByUsername = administratorService.getByUsername(username);
        assertNotNull(administratorServiceByUsername);
        String username1="wjh1";
        Administrator administratorServiceByUsername1 = administratorService.getByUsername(username1);
        assertNotNull(administratorServiceByUsername1);
    }

    @Test
    void loginSuccess() throws ClientException {
        AdministratorLoginInDTO administratorLoginInDTO = new AdministratorLoginInDTO();
        administratorLoginInDTO.setPassword("123456");
        administratorLoginInDTO.setUsername("wjh");
        AdministratorLoginOutDTO login = administratorController.login(administratorLoginInDTO);
        assertNotNull(login);
    }

    @Test
    void loginWrongUsername(){
        AdministratorLoginInDTO administratorLoginInDTO = new AdministratorLoginInDTO();
        administratorLoginInDTO.setPassword("12346");
        administratorLoginInDTO.setUsername("wjh");
        try {
            AdministratorLoginOutDTO login = administratorController.login(administratorLoginInDTO);
        }catch (ClientException ex){
            String errCode = ex.getErrCode();
            assertEquals(ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE,errCode);
        }

    }

    @Test
    void loginWrongpassword(){
        AdministratorLoginInDTO administratorLoginInDTO = new AdministratorLoginInDTO();
        administratorLoginInDTO.setPassword("12346");
        administratorLoginInDTO.setUsername("wjh");
        try {
            AdministratorLoginOutDTO login = administratorController.login(administratorLoginInDTO);
        }catch (ClientException ex){
            String errCode = ex.getErrCode();
            System.out.println(errCode);
            assertEquals(ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE,errCode);
        }

    }

}

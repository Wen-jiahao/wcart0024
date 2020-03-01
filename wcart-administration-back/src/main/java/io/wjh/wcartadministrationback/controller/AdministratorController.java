package io.wjh.wcartadministrationback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.wjh.wcartadministrationback.constant.ClientExceptionConstant;
import io.wjh.wcartadministrationback.dto.in.*;
import io.wjh.wcartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import io.wjh.wcartadministrationback.dto.out.AdministratorLoginOutDTO;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.exception.ClientException;
import io.wjh.wcartadministrationback.po.Administrator;
import io.wjh.wcartadministrationback.service.AdministratorService;
import io.wjh.wcartadministrationback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/login")
    //创建
    public AdministratorLoginOutDTO  login(AdministratorLoginInDTO  administratorLoginInDTO) throws ClientException {
        Administrator administrator = administratorService.getByUsername(administratorLoginInDTO.getUsername());
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encPwdDB = administrator.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(administratorLoginInDTO.getPassword().toCharArray(), encPwdDB);

        if (result.verified) {
            AdministratorLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(administrator);
            return administratorLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }
    }
    //登录后管理员自己拿到自己信息
    //administartorIds是token
    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(@RequestAttribute Integer administartorId){
        Administrator administrator = administratorService.getById(administartorId);
        AdministratorGetProfileOutDTO administratorGetProfileOutDTO = new AdministratorGetProfileOutDTO();
        administratorGetProfileOutDTO.setEmail(administrator.getEmail());
        administratorGetProfileOutDTO.setRealName(administrator.getRealName());
        administratorGetProfileOutDTO.setUsername(administrator.getUsername());
        administratorGetProfileOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());
        administratorGetProfileOutDTO.setAdministartorId(administrator.getAdministratorId());
        administratorGetProfileOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        return administratorGetProfileOutDTO;
    }

    @PostMapping("/updateProfile")
    public void  updateProfile(@RequestBody AdministratorUpdatefileDTO administratorUpdatefileDTO,@RequestAttribute Integer administratorId){

    }
    @GetMapping("/getPwdResetCode")
    public String  getPwdResetCode(@RequestParam String email){
        return null;
    }
    @PostMapping("/resetPwd")
    public void  resetPwd(@RequestBody AdministratorResetPwd administratorResetPwd){

    }
    @GetMapping("/getList")
    public PageOutDTO<AdministratorListDTO> getList(@RequestParam Integer pageNum){
        return null;
    }
    @GetMapping("getById")
    public AdministratorShowOutDTO getById(@RequestParam Integer administratorId){
        return null;
    }
    //添加返回主键id
    @PostMapping("/creeate")
    public Integer create(@RequestBody AdminisyratorCreateDTO adminisyratorCreateDTO){
        return null;
    }
    @PostMapping("/update")
    public void update(@RequestBody AdminisyratorUpdateDTO adminisyratorUpdateDT){

    }
    @PostMapping("/delete")
    public void delete(@RequestBody Integer administratorId){

    }
    //批量删除
    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> administratorIds){

    }
    @PostMapping("/changPwd")
    public void changPwd(@RequestBody AdministratorChangePwdInDTO administratorChangePwdInDTO,@RequestAttribute Integer administroId){

    }
}

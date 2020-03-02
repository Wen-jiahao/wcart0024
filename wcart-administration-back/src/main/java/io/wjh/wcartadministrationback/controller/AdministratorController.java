package io.wjh.wcartadministrationback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.wjh.wcartadministrationback.constant.ClientExceptionConstant;
import io.wjh.wcartadministrationback.dto.in.*;
import io.wjh.wcartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import io.wjh.wcartadministrationback.dto.out.AdministratorLoginOutDTO;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.enumeration.AdministratorStatus;
import io.wjh.wcartadministrationback.exception.ClientException;
import io.wjh.wcartadministrationback.po.Administrator;
import io.wjh.wcartadministrationback.service.AdministratorService;
import io.wjh.wcartadministrationback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/*
无状态 token（通行证）
jwt  ：json web tokken token标准
token包含用户的信息和登录的信息

filter过滤器  适用于所有的web
intercepter 拦截器
浏览器的存储
key/value存贮
1,cookie
2.localstorage存值磁盘上的  持久化 大小的限制大于cookie
3.sessionstoragr 内存
 */

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
        char[] chars = administratorLoginInDTO.getPassword().toCharArray();
        BCrypt.Result result = BCrypt.verifyer().verify(administratorLoginInDTO.getPassword().toCharArray(), encPwdDB);
        System.out.println(result);
        if (result.verified) {
            AdministratorLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(administrator);
            return administratorLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }
    }
    //登录后管理员自己拿到自己信息
    //administartorIds是解析token拿到的
    //无状态:优势他不依赖于服务的存储,依靠cpu来计算适合分布式和集群
    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(@RequestAttribute Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
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
    @PostMapping("/create")
    public Integer create(@RequestBody AdminisyratorCreateDTO adminisyratorCreateDTO){
        /*private String username;
        private  Byte status;
        private  String avatarUrl;
        private  String email;
        private String password;*/
        Administrator administrator = new Administrator();
        administrator.setUsername(adminisyratorCreateDTO.getUsername());
        administrator.setAvatarUrl(adminisyratorCreateDTO.getAvatarUrl());
        administrator.setEmail(adminisyratorCreateDTO.getEmail());
        String password = BCrypt.withDefaults().hashToString(12, adminisyratorCreateDTO.getPassword().toCharArray());
        administrator.setEncryptedPassword(password);
        administrator.setStatus((byte) AdministratorStatus.Enable.ordinal());
        administrator.setCreateTime(new Date());
        administrator.setRealName(adminisyratorCreateDTO.getRealname());
        Integer integer = administratorService.create(administrator);
        return integer;
    }
    @PostMapping("/update")
    public void update(@RequestBody AdminisyratorUpdateDTO adminisyratorUpdateDT){
        administratorService.update(adminisyratorUpdateDT);
    }
    @PostMapping("/delete")
    public void delete(@RequestBody Integer administratorId){
        administratorService.delete(administratorId);
    }
    //批量删除
    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> administratorIds){
        administratorService.batchDelete(administratorIds);
    }
    @PostMapping("/changPwd")
    public void changPwd(@RequestBody AdministratorChangePwdInDTO administratorChangePwdInDTO,@RequestAttribute Integer administroId){

    }
}

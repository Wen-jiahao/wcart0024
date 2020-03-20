package io.wjh.wcartadministrationback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.constant.ClientExceptionConstant;
import io.wjh.wcartadministrationback.dto.in.*;
import io.wjh.wcartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import io.wjh.wcartadministrationback.dto.out.AdministratorLoginOutDTO;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.enumeration.AdministratorStatus;
import io.wjh.wcartadministrationback.exception.ClientException;
import io.wjh.wcartadministrationback.po.Administrator;
import io.wjh.wcartadministrationback.service.AdministratorService;
import io.wjh.wcartadministrationback.util.EmailUtil;
import io.wjh.wcartadministrationback.util.JWTUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import java.security.SecureRandom;

import org.springframework.web.bind.annotation.*;



import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@CrossOrigin
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private JWTUtil jwtUtil;
   @Autowired
   private SecureRandom secureRandom;

   @Autowired
   private RocketMQTemplate rocketMQTemplate;




    private Map<String,String> emailPwdRestMap=new HashMap<>();

    @Value("${spring.mail.username}")
    private String fromEmail;
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
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorId);
        administrator.setUsername(administratorUpdatefileDTO.getUsername());
        administrator.setRealName(administratorUpdatefileDTO.getRealName());
        administrator.setAvatarUrl(administratorUpdatefileDTO.getAvatarUrl());
        administrator.setEmail(administratorUpdatefileDTO.getEmail());
        administratorService.updateProfile(administrator);
    }
    //获取重置码
    @GetMapping("/getPwdResetCode")
    public void   getPwdResetCode(@RequestParam String email) throws ClientException {
        Administrator administrator = administratorService.getByEmail(email);
        if(administrator==null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRCODE,ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRMSG);
        }
        //生成随机数据
        byte[] bytes = secureRandom.generateSeed(3);
        String hex = DatatypeConverter.printHexBinary(bytes);
        emailUtil.sendEmail(fromEmail,email,hex);
        emailPwdRestMap.put(email, hex);
    }
    @PostMapping("/resetPwd")
    public void  resetPwd(@RequestBody AdministratorResetPwd administratorResetPwd) throws ClientException {
        String email = administratorResetPwd.getEmail();
        if (email==null){
            throw  new ClientException(ClientExceptionConstant.ADNINISTRATOR_PWDRESET_EMAIL_NONE_ERRCODE,ClientExceptionConstant.ADNINISTRATOR_PWDRESET_EMAIL_NONE_ERRMSG);
        }
        String innerResetCode = emailPwdRestMap.get(email);
        if (innerResetCode == null) {
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PWDRESET_INNER_RESETCODE_NONE_ERRCODE, ClientExceptionConstant.ADNINISTRATOR_PWDRESET_INNER_RESETCODE_NONE_ERRMSG);
        }
        //判断是否为空
        String outerResetCode = administratorResetPwd.getRestCode();
        if (outerResetCode == null) {
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PWDRESET_OUTER_RESETCODE_NONE_ERRCODE, ClientExceptionConstant.ADNINISTRATOR_PWDRESET_OUTER_RESETCODE_NONE_ERRMSG);
        }
        //比较
        if (!outerResetCode.equalsIgnoreCase(innerResetCode)){
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PWDRESET_RESETCODE_INVALID_ERRCODE, ClientExceptionConstant.ADNINISTRATOR_PWDRESET_RESETCODE_INVALID_ERRMSG);
        }
        Administrator administrator = administratorService.getByEmail(email);
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRMSG);
        }
        String newPwd = administratorResetPwd.getNewPwd();
        if (newPwd == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_NEWPWD_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_NEWPWD_NOT_EXIST_ERRMSG);
        }
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, newPwd.toCharArray());
        administrator.setEncryptedPassword(bcryptHashString);
        administratorService.updates(administrator);
        emailPwdRestMap.remove(email);


    }
    @GetMapping("/getList")
    public PageOutDTO<AdministratorListDTO> getList(@RequestParam(required = false, defaultValue = "1") Integer pageNum){
        Page<Administrator> list=administratorService.getList(pageNum);
        PageOutDTO<AdministratorListDTO> administratorListDTOPageOutDTO = new PageOutDTO<>();
        administratorListDTOPageOutDTO.setTotal(list.getTotal());
        List<AdministratorListDTO> administratorListOutDTOS  = list.stream().map(administrator -> {
            AdministratorListDTO administratorListOutDTO = new AdministratorListDTO();
            administratorListOutDTO.setAdministratorId(administrator.getAdministratorId());
            administratorListOutDTO.setUsername(administrator.getUsername());
            administratorListOutDTO.setRealname(administrator.getRealName());
            administratorListOutDTO.setStatus(administrator.getStatus());
            administratorListOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());
            return administratorListOutDTO;
        }).collect(Collectors.toList());
        administratorListDTOPageOutDTO.setList(administratorListOutDTOS);
        administratorListDTOPageOutDTO.setPageNum(list.getPageNum());
        administratorListDTOPageOutDTO.setPageSize(list.getPageSize());
        return administratorListDTOPageOutDTO;
    }
    @GetMapping("getById")
    public AdministratorShowOutDTO getById(@RequestParam Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorShowOutDTO administratorShowOutDTO = new AdministratorShowOutDTO();
        administratorShowOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorShowOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorShowOutDTO.setEmail(administrator.getEmail());
        administratorShowOutDTO.setStatus(administrator.getStatus());
        administratorShowOutDTO.setUsername(administrator.getUsername());
        administratorShowOutDTO.setRealname(administrator.getRealName());
        return administratorShowOutDTO;
    }
    //添加返回主键id
    @PostMapping("/create")
    public Integer create(@RequestBody AdminisyratorCreateDTO adminisyratorCreateDTO) throws ClientException {
        /*private String username;
        private  Byte status;
        private  String avatarUrl;
        private  String email;
        private String password;*/
        Administrator administrator = new Administrator();
        String username = adminisyratorCreateDTO.getUsername();


        administrator.setUsername(username);
        administrator.setAvatarUrl(adminisyratorCreateDTO.getAvatarUrl());
        administrator.setRealName(adminisyratorCreateDTO.getRealname());
        String email1 = adminisyratorCreateDTO.getEmail();

        administrator.setEmail(email1);
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

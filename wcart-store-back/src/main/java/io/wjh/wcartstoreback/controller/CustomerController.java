package io.wjh.wcartstoreback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.wjh.wcartstoreback.constant.ClientExceptionConstant;
import io.wjh.wcartstoreback.dto.in.CustomerChangePwdInDTO;
import io.wjh.wcartstoreback.dto.in.CustomerLoginInDTO;
import io.wjh.wcartstoreback.dto.in.CustomerRegisterInDTO;
import io.wjh.wcartstoreback.dto.in.CustomerUpdateProfileInDTO;
import io.wjh.wcartstoreback.dto.out.CustomerGetProfileOutDTO;
import io.wjh.wcartstoreback.dto.out.CustomerLoginOutDTO;
import io.wjh.wcartstoreback.exception.ClientException;
import io.wjh.wcartstoreback.po.Customer;
import io.wjh.wcartstoreback.service.CustomerService;
import io.wjh.wcartstoreback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import java.security.SecureRandom;


import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private JWTUtil jwtUtil;

   /* @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private JavaMailSender mailSender;*/

    private HashMap<String, String> emailPwdResetCodeMap = new HashMap();

   /* @Value("${spring.mail.username}")
    private String fromEmail;*/
    @PostMapping("/register")
    public Integer register(@RequestBody CustomerRegisterInDTO customerRegisterInDTO ){
        return customerService.register(customerRegisterInDTO);
    }
    @GetMapping("/login")
    public CustomerLoginOutDTO login(CustomerLoginInDTO customerLoginInDTO) throws ClientException {
        Customer customer = customerService.getByUsername(customerLoginInDTO.getUsername());
        if (customer == null){
            throw new ClientException(ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encPwdDB = customer.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(customerLoginInDTO.getPassword().toCharArray(), encPwdDB);

        if (result.verified) {
            CustomerLoginOutDTO customerLoginOutDTO = jwtUtil.issueToken(customer);
            return customerLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRMSG);
        }
    }

   @GetMapping("/getProfile")
    public CustomerGetProfileOutDTO getProfile(@RequestAttribute Integer customerId){
       Customer customer = customerService.getById(customerId);
       CustomerGetProfileOutDTO customerGetProfileOutDTO = new CustomerGetProfileOutDTO();
       customerGetProfileOutDTO.setUsername(customer.getUsername());
       customerGetProfileOutDTO.setRealName(customer.getRealName());
       customerGetProfileOutDTO.setMobile(customer.getMobile());
       customerGetProfileOutDTO.setMobileVerified(customer.getMobileVerified());
       customerGetProfileOutDTO.setEmail(customer.getEmail());
       customerGetProfileOutDTO.setEmailVerified(customer.getEmailVerified());
        return customerGetProfileOutDTO;
    }

    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody CustomerUpdateProfileInDTO customerUpdateProfileInDTO,
                              @RequestAttribute Integer customerId){
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setRealName(customerUpdateProfileInDTO.getRealName());
        customer.setMobile(customerUpdateProfileInDTO.getMobile());
        customer.setEmail(customerUpdateProfileInDTO.getEmail());
        customerService.update(customer);

    }

    @PostMapping("/changePwd")
    public void changePwd(@RequestBody CustomerChangePwdInDTO customerChangePwdInDTO,
                          @RequestAttribute Integer customerId) throws ClientException {
        Customer customer = customerService.getById(customerId);
        String encPwdDB = customer.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(customerChangePwdInDTO.getOriginPwd().toCharArray(), encPwdDB);

        if (result.verified) {
            String newPwd = customerChangePwdInDTO.getNewPwd();
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, newPwd.toCharArray());
            customer.setEncryptedPassword(bcryptHashString);
            customerService.update(customer);
        }else {
            throw new ClientException(ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRMSG);
        }

    }

/*    @GetMapping("/getPwdResetCode")
    public void getPwdResetCode(@RequestParam String email) throws ClientException {
        Customer customer = customerService.getByEmail(email);
        if (customer == null){
            throw new ClientException(ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }
        byte[] bytes = secureRandom.generateSeed(3);
        String hex = DatatypeConverter.printHexBinary(bytes);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("jcart重置密码");
        message.setText(hex);
        mailSender.send(message);
        emailPwdResetCodeMap.put("PwdResetCode"+email, hex);
    }*/

 /*   @PostMapping("/resetPwd")
    public void resetPwd(@RequestBody CustomerResetPwdInDTO customerResetPwdInDTO){

    }*/

}

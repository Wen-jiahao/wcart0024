package io.wjh.wcartadministrationback.controller;

import io.wjh.wcartadministrationback.dto.in.*;
import io.wjh.wcartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {


    @GetMapping("/login")
    //创建
    public String login(AdministratorLoginDTO administratorLoginDTO){
        return null;
    }

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(Integer administartorId){
        return null;
    }

    @PostMapping("/updateProfile")
    public void  updateProfile(@RequestBody AdministratorUpdatefileDTO administratorUpdatefileDTO){

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
}

package io.wjh.wcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.ReturnSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.ReturnUpdateActionInDTO;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.dto.out.ReturnListOutDTO;
import io.wjh.wcartadministrationback.dto.out.ReturnShowOutDTO;
import io.wjh.wcartadministrationback.po.Return;
import io.wjh.wcartadministrationback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/return")
@CrossOrigin
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    /*   1.	return search
        2.return show（returnhistory list）
         3.	return action update
        4.	returnhistory create*/
    @GetMapping("search")
    public PageOutDTO<ReturnListOutDTO> search(@RequestBody ReturnSearchInDTO returnSearchInDTO, @RequestParam(required = false, defaultValue = "1") Integer pageNum){
        Page<Return> returns=returnService.search(returnSearchInDTO,pageNum);
        List<ReturnListOutDTO> returnList = returns.stream().map(aReturn -> {
            ReturnListOutDTO returnListOutDTO = new ReturnListOutDTO();
            returnListOutDTO.setCreateTime(aReturn.getCreateTime().getTime());
            returnListOutDTO.setCustomerId(aReturn.getCustomerId());
            returnListOutDTO.setCustomerName(aReturn.getCustomerName());
            returnListOutDTO.setOrderId(aReturn.getOrderId());
            returnListOutDTO.setProductCode(aReturn.getProductCode());
            returnListOutDTO.setCreateTime(aReturn.getCreateTime().getTime());
            returnListOutDTO.setReturnId(aReturn.getReturnId());
            returnListOutDTO.setStatus(aReturn.getStatus());
            return returnListOutDTO;
        }).collect(Collectors.toList());
        PageOutDTO<ReturnListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(returns.getTotal());
        pageOutDTO.setPageSize(returns.getPageSize());
        pageOutDTO.setPageNum(returns.getPageNum());
        pageOutDTO.setList(returnList);

        return pageOutDTO;
    }
    @GetMapping("/getById")
    public ReturnShowOutDTO getById(@RequestParam Integer returnId){
       Return aReturn=returnService.getById(returnId);
        ReturnShowOutDTO returnShowOutDTO = new ReturnShowOutDTO();
        returnShowOutDTO.setReturnId(aReturn.getReturnId());
        returnShowOutDTO.setOrderId(aReturn.getOrderId());
        returnShowOutDTO.setOrderTimestamp(aReturn.getOrderTime().getTime());
        returnShowOutDTO.setCustomerId(aReturn.getCustomerId());
        returnShowOutDTO.setCustomerName(aReturn.getCustomerName());
        returnShowOutDTO.setMobile(aReturn.getMobile());
        returnShowOutDTO.setEmail(aReturn.getEmail());
        returnShowOutDTO.setStatus(aReturn.getStatus());
        returnShowOutDTO.setAction(aReturn.getAction());
        returnShowOutDTO.setProductCode(aReturn.getProductCode());
        returnShowOutDTO.setProductName(aReturn.getProductName());
        returnShowOutDTO.setQuantity(aReturn.getQuantity());
        returnShowOutDTO.setReason(aReturn.getReason());
        returnShowOutDTO.setOpened(aReturn.getOpened());
        returnShowOutDTO.setComment(aReturn.getComment());
        returnShowOutDTO.setCreateTimestamp(aReturn.getCreateTime().getTime());
        returnShowOutDTO.setUpdateTimestamp(aReturn.getUpdateTime().getTime());

        return returnShowOutDTO;
    }

    //修改状态
    @PostMapping("/updateAction")
    public void updateAction(@RequestBody ReturnUpdateActionInDTO returnUpdateActionInDTO){

        returnService.updateAction(returnUpdateActionInDTO);
    }

}

package io.wjh.wcartstoreback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.dto.in.ReturnApplyInDTO;
import io.wjh.wcartstoreback.dto.out.PageOutDTO;
import io.wjh.wcartstoreback.dto.out.ReturnHistoryListOutDTO;
import io.wjh.wcartstoreback.dto.out.ReturnListOutDTO;
import io.wjh.wcartstoreback.dto.out.ReturnShowOutDTO;
import io.wjh.wcartstoreback.enumeration.ReturnStatus;
import io.wjh.wcartstoreback.po.Return;
import io.wjh.wcartstoreback.po.ReturnHistory;
import io.wjh.wcartstoreback.service.ReturnHistoryService;
import io.wjh.wcartstoreback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/return")
@CrossOrigin
public class ReturnController {

    @Autowired
    private ReturnService returnService;
    @Autowired
    private ReturnHistoryService returnHistoryService;


    //申请退户
    @PostMapping("/apply")
    public Integer apply(@RequestBody ReturnApplyInDTO returnApplyInDTO,@RequestAttribute Integer customerId){
        Return aReturn = new Return();
        aReturn.setOrderId(returnApplyInDTO.getOrderId());
        aReturn.setOrderTime(new Date(returnApplyInDTO.getOrderTimestamp()));
        aReturn.setCustomerId(customerId);
        aReturn.setCustomerName(returnApplyInDTO.getCustomerName());
        aReturn.setMobile(returnApplyInDTO.getMobile());
        aReturn.setEmail(returnApplyInDTO.getEmail());
        aReturn.setStatus((byte) ReturnStatus.ToProcess.ordinal());//处理中
        aReturn.setProductCode(returnApplyInDTO.getProductCode());
        aReturn.setProductName(returnApplyInDTO.getProductName());
        aReturn.setQuantity(returnApplyInDTO.getQuantity());
        aReturn.setReason(returnApplyInDTO.getReason());
        aReturn.setOpened(returnApplyInDTO.getOpened());
        aReturn.setComment(returnApplyInDTO.getComment());
        Date now = new Date();
        aReturn.setCreateTime(now);
        aReturn.setUpdateTime(now);
        returnService.create(aReturn);
        Integer returnId = aReturn.getReturnId();
        return returnId;
    }

    @GetMapping("/getList")
    public PageOutDTO<ReturnListOutDTO> getList(@RequestAttribute Integer customerId,
                                                @RequestParam Integer pageNum){
       Page<Return> returnList= returnService.getList(customerId,pageNum);
        List<ReturnListOutDTO> returnLists = returnList.stream().map(aReturn -> {
            ReturnListOutDTO returnListOutDTO = new ReturnListOutDTO();
            returnListOutDTO.setReturnId(aReturn.getReturnId());
            returnListOutDTO.setOrderId(aReturn.getOrderId());
            returnListOutDTO.setCustomerId(aReturn.getCustomerId());
            returnListOutDTO.setCustomerName(aReturn.getCustomerName());
            returnListOutDTO.setStatus(aReturn.getStatus());
            returnListOutDTO.setCreateTimestamp(aReturn.getCreateTime().getTime());
            return returnListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<ReturnListOutDTO> returnListOutDTOPageOutDTO = new PageOutDTO<>();
        returnListOutDTOPageOutDTO.setList(returnLists);
        returnListOutDTOPageOutDTO.setPageSize(returnList.getPageSize());
        returnListOutDTOPageOutDTO.setTotal(returnList.getTotal());
        returnListOutDTOPageOutDTO.setPageNum(returnList.getPageNum());
        return returnListOutDTOPageOutDTO;
    }
    @GetMapping("/getById")
    public ReturnShowOutDTO getById(@RequestParam Integer returnId) {
        Return aReturn= returnService.getById(returnId);
        ReturnShowOutDTO returnShowOutDTO = new ReturnShowOutDTO();
        returnShowOutDTO.setReturnId(aReturn.getReturnId());
        returnShowOutDTO.setOrderId(aReturn.getOrderId());
        returnShowOutDTO.setOrderTimestamp(aReturn.getOrderTime().getTime());
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
        returnShowOutDTO.setCreateTimestamp(aReturn.getCreateTime().getTime());
        returnShowOutDTO.setUpdateTimestamp(aReturn.getUpdateTime().getTime());
        List<ReturnHistory> returnHistories = returnHistoryService.getByReturnId(returnId);
        List<ReturnHistoryListOutDTO> returnHistoryListOutDTOS = returnHistories.stream().map(returnHistory -> {
            ReturnHistoryListOutDTO returnHistoryListOutDTO = new ReturnHistoryListOutDTO();
            returnHistoryListOutDTO.setTimestamp(returnHistory.getTime().getTime());
            returnHistoryListOutDTO.setReturnStatus(returnHistory.getReturnStatus());
            returnHistoryListOutDTO.setComment(returnHistory.getComment());
            return returnHistoryListOutDTO;
        }).collect(Collectors.toList());
        returnShowOutDTO.setReturnHistories(returnHistoryListOutDTOS);
        return returnShowOutDTO;

    }

}

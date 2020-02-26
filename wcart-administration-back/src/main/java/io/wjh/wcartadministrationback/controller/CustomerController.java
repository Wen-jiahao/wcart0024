package io.wjh.wcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.CustomerSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.ProductSearchInDTO;
import io.wjh.wcartadministrationback.dto.out.CustomerListOutDTO;
import io.wjh.wcartadministrationback.dto.out.CustomerShowOutDTD;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    /* 1.	customer search
    2.	customer show  getById
    3.	customer disable
    4.	address show*/
    @Autowired
    private CustomerService customerService;

    @GetMapping("/search")
    public PageOutDTO<CustomerListOutDTO> search(CustomerSearchInDTO customerSearchInDTO, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<CustomerListOutDTO> customerList=customerService.search(pageNum);
        PageOutDTO<CustomerListOutDTO> PageOutDTO = new PageOutDTO<>();
        PageOutDTO.setPageSize(customerList.getPageSize());
        PageOutDTO.setPageNum(customerList.getPageNum());
        PageOutDTO.setList(customerList);
        PageOutDTO.setTotal(customerList.getTotal());
        return PageOutDTO;
    }

    @GetMapping("/getById")
    public CustomerShowOutDTD getById(@RequestParam Integer customerId){
        return null;
    }
    @PostMapping("/disable")
    public void disable(@RequestParam Integer customerId){

    }
}

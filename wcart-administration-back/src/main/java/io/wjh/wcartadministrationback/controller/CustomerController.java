package io.wjh.wcartadministrationback.controller;

import io.wjh.wcartadministrationback.dto.in.CustomerSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.ProductSearchInDTO;
import io.wjh.wcartadministrationback.dto.out.CustomerListOutDTO;
import io.wjh.wcartadministrationback.dto.out.CustomerShowOutDTD;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    /* 1.	customer search
    2.	customer show  getById
    3.	customer disable
    4.	address show*/

    @GetMapping("/search")
    public PageOutDTO<CustomerListOutDTO> search(@RequestBody CustomerSearchInDTO customerSearchInDTO, @RequestParam Integer pageNum){
        return null;
    }

    @GetMapping("/getById")
    public CustomerShowOutDTD getById(@RequestParam Integer customerId){
        return null;
    }
    @PostMapping("/disable")
    public void disable(@RequestParam Integer customerId){

    }
}

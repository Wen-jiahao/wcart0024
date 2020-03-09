package io.wjh.wcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.CustomerSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.CustomerSetStatusInDTO;
import io.wjh.wcartadministrationback.dto.in.ProductSearchInDTO;
import io.wjh.wcartadministrationback.dto.out.CustomerListOutDTO;
import io.wjh.wcartadministrationback.dto.out.CustomerShowOutDTD;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.po.Address;
import io.wjh.wcartadministrationback.po.Customer;
import io.wjh.wcartadministrationback.service.AddressService;
import io.wjh.wcartadministrationback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    /* 1.	customer search
    2.	customer show  getById
    3.	customer disable
    4.	address show*/
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/search")
    public PageOutDTO<CustomerListOutDTO> search(CustomerSearchInDTO customerSearchInDTO, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<Customer> customerList=customerService.searchCustomer(pageNum,customerSearchInDTO);
        List<CustomerListOutDTO> customerLists = customerList.stream().map(customer -> {
            CustomerListOutDTO customerListOutDTO = new CustomerListOutDTO();
            customerListOutDTO.setCustomerId(customer.getCustomerId());
            customerListOutDTO.setUsername(customer.getUsername());
            customerListOutDTO.setRealName(customer.getRealName());
            customerListOutDTO.setMobile(customer.getMobile());
            customerListOutDTO.setEmail(customer.getEmail());
            customerListOutDTO.setStatus(customer.getStatus());
            customerListOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
            return customerListOutDTO;
        }).collect(Collectors.toList());
        PageOutDTO<CustomerListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(customerList.getTotal());
        pageOutDTO.setPageSize(customerList.getPageSize());
        pageOutDTO.setPageNum(customerList.getPageNum());
        pageOutDTO.setList(customerLists);
        return pageOutDTO;
    }

    @GetMapping("/getById")
    public CustomerShowOutDTD getById(@RequestParam Integer customerId){
        Customer customer= customerService.getById(customerId);
        CustomerShowOutDTD customerShowOutDTO = new CustomerShowOutDTD();
        customerShowOutDTO.setCustomerId(customerId);
        customerShowOutDTO.setUsername(customer.getUsername());
        customerShowOutDTO.setRealName(customer.getRealName());
        customerShowOutDTO.setMobile(customer.getMobile());
        customerShowOutDTO.setEmail(customer.getEmail());
        customerShowOutDTO.setAvatarUrl(customer.getAvatarUrl());
        customerShowOutDTO.setStatus(customer.getStatus());
        customerShowOutDTO.setRewordPoints(customer.getRewordPoints());
        customerShowOutDTO.setNewsSubscribed(customer.getNewsSubscribed());
        customerShowOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
        customerShowOutDTO.setDefaultAddressId(customer.getDefaultAddressId());
        //默認地址
        Address defaultAddress = addressService.getById(customer.getDefaultAddressId());
        if (defaultAddress != null){
            customerShowOutDTO.setDefaultAddress(defaultAddress.getContent());
        }
        return customerShowOutDTO;
    }
    @PostMapping("/disable")
    public void disable(@RequestBody CustomerSetStatusInDTO customerSetStatusInDTO){
        customerService.disable(customerSetStatusInDTO);
    }
}

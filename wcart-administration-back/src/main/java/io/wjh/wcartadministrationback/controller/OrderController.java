package io.wjh.wcartadministrationback.controller;

import io.wjh.wcartadministrationback.dto.in.OrderSearchInDTO;
import io.wjh.wcartadministrationback.dto.out.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
 /* 1.order search
      2.order show（order history in order）
        3.	orderhistory add
       4.invoice info show
       */
     @GetMapping("/search")
    public PageOutDTO<OrderListOutDTD> search(@RequestBody OrderSearchInDTO orderSearchInDTO, @RequestParam Integer pageNum){
         return null;
     }
    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Integer orderId){
        return null;
    }
    @GetMapping("/getInvoiceInfo")
    public OrderInvoiceShowOutDTO getInvoiceInfo(@RequestParam Long orderId){
         return null;
    }
    @GetMapping("getshipInfo")
    public ShipShowOutDTO getshipInfo(@RequestParam Long orderId){
        return null;
    }




}

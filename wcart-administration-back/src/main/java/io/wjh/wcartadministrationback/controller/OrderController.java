package io.wjh.wcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.in.OrderSearchInDTO;
import io.wjh.wcartadministrationback.dto.out.*;
import io.wjh.wcartadministrationback.po.Order;
import io.wjh.wcartadministrationback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
 /* 1.order search
      2.order show（order history in order）
        3.	orderhistory add
       4.invoice info show
       */

    @Autowired
    private OrderService orderService;

     @GetMapping("/search")
    public PageOutDTO<OrderListOutDTD> search( OrderSearchInDTO orderSearchInDTO, @RequestParam(defaultValue = "1",required = false) Integer pageNum){
         Page<Order>  orderListpage= orderService.search(pageNum);
         PageOutDTO<OrderListOutDTD> orderListOutDTDPageOutDTO = new PageOutDTO<>();
         orderListOutDTDPageOutDTO.setPageSize(orderListpage.getPageSize());
         orderListOutDTDPageOutDTO.setPageNum(orderListpage.getPageNum());
         orderListOutDTDPageOutDTO.setTotal(orderListpage.getTotal());
         List<OrderListOutDTD> collect = orderListpage.stream().map(orderList -> {
             OrderListOutDTD orderListOutDTD = new OrderListOutDTD();
             orderListOutDTD.setCreateTime(orderList.getCreateTime().getTime());
             orderListOutDTD.setCustomerId(orderList.getCustomerId());
             orderListOutDTD.setOrderId(orderList.getOrderId());
             orderListOutDTD.setStatus(orderList.getStatus());
             orderListOutDTD.setTotalPrice(orderList.getTotalPrice());
             orderListOutDTD.setUpdateTime(orderList.getUpdateTime().getTime());
             return orderListOutDTD;
         }).collect(Collectors.toList());
         orderListOutDTDPageOutDTO.setList(collect);
         return orderListOutDTDPageOutDTO;
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

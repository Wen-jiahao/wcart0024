package io.wjh.wcartadministrationback.controller;

import io.wjh.wcartadministrationback.dto.in.OrderHistoryCreateInDTO;
import io.wjh.wcartadministrationback.dto.out.OrderHistoryListOutDTO;
import io.wjh.wcartadministrationback.po.OrderHistory;
import io.wjh.wcartadministrationback.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderhistory")
@CrossOrigin
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;
    //根据订单id查询出订单历史
    @GetMapping("/getListByOrderId")
    public List<OrderHistoryListOutDTO> getListByOrderId(@RequestParam Long orderId){
       List<OrderHistory> orderHistoryList= orderHistoryService.getListByOrderId(orderId);
        List<OrderHistoryListOutDTO> OrderHistory = orderHistoryList.stream().map(orderHistory -> {
            OrderHistoryListOutDTO orderHistoryListOutDTO = new OrderHistoryListOutDTO();
            orderHistoryListOutDTO.setComment(orderHistory.getComment());
            orderHistoryListOutDTO.setCustomeNotified(orderHistory.getCustomerNotified());
            orderHistoryListOutDTO.setOrderHistoryId(orderHistory.getOrderHistoryId());
            orderHistoryListOutDTO.setOrderStatus(orderHistory.getOrderStatus());
            orderHistoryListOutDTO.setTimestamp(orderHistory.getTime().getTime());
            return orderHistoryListOutDTO;
        }).collect(Collectors.toList());
        return OrderHistory;
    }
    @PostMapping("/create")
    public Long create(@RequestBody OrderHistoryCreateInDTO orderHistoryCreateInDTO){
       Long orederId= orderHistoryService.create(orderHistoryCreateInDTO);
        return orederId;
    }

}

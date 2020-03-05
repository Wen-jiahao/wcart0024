package io.wjh.wcartstoreback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartstoreback.dto.in.OrderCheckoutInDTO;
import io.wjh.wcartstoreback.dto.out.OrderListOutDTO;
import io.wjh.wcartstoreback.dto.out.OrderShowOutDTO;
import io.wjh.wcartstoreback.dto.out.PageOutDTO;
import io.wjh.wcartstoreback.po.Order;
import io.wjh.wcartstoreback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/checkout")
    public Long checkout(@RequestBody OrderCheckoutInDTO orderCheckoutInDTO,@RequestAttribute Integer customerId){
        Long orderId=orderService.checkout(orderCheckoutInDTO,customerId);
        return orderId;
    }

    @GetMapping("/getList")
    public PageOutDTO<OrderListOutDTO> getList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                               @RequestAttribute Integer customerId){
        Page<Order> page= orderService.getByCustomerId(customerId,pageNum);
        List<OrderListOutDTO> orderListOutDTOs = page.stream().map(order -> {
            OrderListOutDTO orderListOutDTO = new OrderListOutDTO();
            orderListOutDTO.setCreateTimestamp(order.getCreateTime().getTime());
            orderListOutDTO.setCustomerId(order.getCustomerId());
            orderListOutDTO.setOrderId(order.getOrderId());
            orderListOutDTO.setStatus(order.getStatus());
            orderListOutDTO.setTotalPrice(order.getTotalPrice());
            return orderListOutDTO;
        }).collect(Collectors.toList());
        PageOutDTO<OrderListOutDTO> orderListOutDTOPageOutDTO = new PageOutDTO<>();
        orderListOutDTOPageOutDTO.setPageSize(page.getPageNum());
        orderListOutDTOPageOutDTO.setTotal(page.getTotal());
        orderListOutDTOPageOutDTO.setPageSize(page.getPageSize());
        orderListOutDTOPageOutDTO.setList(orderListOutDTOs);
        return orderListOutDTOPageOutDTO;
    }
    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        OrderShowOutDTO  orderShowOutDTO=orderService.getById(orderId);
        return orderShowOutDTO;
    }
}

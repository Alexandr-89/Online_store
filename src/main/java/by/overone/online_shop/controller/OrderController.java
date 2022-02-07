package by.overone.online_shop.controller;

import by.overone.online_shop.model.Order;
import by.overone.online_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{id}")
    public void addOrder(@PathVariable long id){
        System.out.println(id);
        orderService.addOrder(id);
    }
}

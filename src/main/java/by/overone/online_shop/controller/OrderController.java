package by.overone.online_shop.controller;

import by.overone.online_shop.dto.OrderAllInfoDTO;
import by.overone.online_shop.dto.OrderedProductsDTO;
import by.overone.online_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@PathVariable Long id){
        orderService.addOrder(id);
    }

    @GetMapping("/{id}")
    public OrderAllInfoDTO getOrder(@PathVariable Long id){
        return orderService.getOrderInfo(id);
    }

//    @GetMapping("/ordered{id}")
//    public List<OrderedProductsDTO> get (@PathVariable Long id){
//        return orderService.getOrderedProducts(id);
//    }
}

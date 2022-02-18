package by.overone.online_shop.controller;

import by.overone.online_shop.dto.OrderAllInfoDTO;
import by.overone.online_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@PathVariable @Valid @Min(1) Long id){
        orderService.addOrder(id);
    }

    @GetMapping("/{id}")
    public List<OrderAllInfoDTO> getOrderByUserId(@PathVariable @Valid @Min(1) Long id){
            return orderService.getOrdersByUserId(id);
    }

    @GetMapping("/all")
    public List<OrderAllInfoDTO> getAllOrder(){
        return orderService.getAllOrders();
    }


}

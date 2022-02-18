package by.overone.online_shop.service;

import by.overone.online_shop.dto.OrderAllInfoDTO;

import java.util.List;

public interface OrderService {

    void addOrder(long id);
    List<OrderAllInfoDTO> getOrdersByUserId(Long id);
    List<OrderAllInfoDTO> getAllOrders();
}

package by.overone.online_shop.dao;

import by.overone.online_shop.dto.OrderInfoDTO;
import by.overone.online_shop.dto.OrderedProductsDTO;

import java.util.List;

public interface OrderDAO {

    void addOrder(long id);
    List<OrderInfoDTO> getOrdersByUserId(Long id);
    List<OrderInfoDTO> getAllOrders();
    List<OrderedProductsDTO> getOrderedProducts(Long id);
}

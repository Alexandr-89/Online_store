package by.overone.online_shop.dao;

import by.overone.online_shop.dto.OrderInfoDTO;
import by.overone.online_shop.dto.OrderedProductsDTO;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    void addOrder(long id);
    Optional<OrderInfoDTO> getOrderedProducts(Long id);
    List<OrderedProductsDTO> get(Long id);
}

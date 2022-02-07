package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.OrderDAO;
import by.overone.online_shop.dto.OrderAllInfoDTO;
import by.overone.online_shop.dto.OrderInfoDTO;
import by.overone.online_shop.dto.OrderedProductsDTO;
import by.overone.online_shop.exception.EntityNotFoundException;
import by.overone.online_shop.exception.ExceptionCode;
import by.overone.online_shop.model.Order;
import by.overone.online_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Override
    public void addOrder(long id) {
        orderDAO.addOrder(id);
    }

    @Override
    public OrderAllInfoDTO getOrderInfo(Long id) {
        OrderInfoDTO orderInfoDTO = orderDAO.getOrderedProducts(id).orElseThrow(() -> new EntityNotFoundException
                (ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        System.out.println(orderInfoDTO.toString());
        OrderAllInfoDTO orderAllInfoDTO = new OrderAllInfoDTO();
        orderAllInfoDTO.setUsers_id(orderInfoDTO.getUsers_id());
        orderAllInfoDTO.setOrders_id(orderInfoDTO.getOrders_id());
        orderAllInfoDTO.setDate(orderInfoDTO.getDate());
        orderAllInfoDTO.setOrderedProductsDTOS(orderDAO.get(orderAllInfoDTO.getOrders_id()));
        orderAllInfoDTO.setTotal(orderAllInfoDTO.getOrderedProductsDTOS().stream().collect(Collectors.summingDouble(OrderedProductsDTO::getSum)));
        return orderAllInfoDTO;
    }

    @Override
    public List<OrderedProductsDTO> get(Long id) {
        return orderDAO.get(id);
    }
}

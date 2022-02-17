package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.OrderDAO;
import by.overone.online_shop.dto.OrderAllInfoDTO;
import by.overone.online_shop.dto.OrderInfoDTO;
import by.overone.online_shop.dto.OrderedProductsDTO;
import by.overone.online_shop.exception.EntityNotFoundException;
import by.overone.online_shop.exception.ExceptionCode;
import by.overone.online_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;


    @Override
    public OrderAllInfoDTO getOrderInfo(Long id) {
        OrderInfoDTO orderInfoDTO = orderDAO.getOrderByUserId(id).orElseThrow(() -> new EntityNotFoundException
                (ExceptionCode.NOT_EXISTING_ORDER_BY_USER_ID.getErrorCode()));
        OrderAllInfoDTO orderAllInfoDTO = new OrderAllInfoDTO();
        orderAllInfoDTO.setUsers_id(orderInfoDTO.getUsers_id());
        orderAllInfoDTO.setOrders_id(orderInfoDTO.getOrders_id());
        orderAllInfoDTO.setDate(orderInfoDTO.getDate());
        orderAllInfoDTO.setOrderedProductsDTOS(orderDAO.getOrderedProducts(orderAllInfoDTO.getOrders_id()));
//        orderAllInfoDTO.setTotal(orderAllInfoDTO.getOrderedProductsDTOS().stream().collect(Collectors.summingDouble(OrderedProductsDTO::getSum)));
        orderAllInfoDTO.setTotal(orderAllInfoDTO.getOrderedProductsDTOS().stream().mapToDouble(OrderedProductsDTO::getSum).sum());
        log.info(orderAllInfoDTO.toString());
        return orderAllInfoDTO;
    }



    @Override
    @Transactional
    public void addOrder(long id) {
        orderDAO.addOrder(id);
    }



//    @Override
//    public List<OrderedProductsDTO> getOrderedProducts(Long id) {
//        return orderDAO.getOrderedProducts(id);
//    }
}

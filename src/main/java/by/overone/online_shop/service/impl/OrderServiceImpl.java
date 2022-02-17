package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.OrderDAO;
import by.overone.online_shop.dto.OrderAllInfoDTO;
import by.overone.online_shop.dto.OrderInfoDTO;
import by.overone.online_shop.dto.OrderedProductsDTO;
import by.overone.online_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;


    @Override
    public List<OrderAllInfoDTO> getOrders(Long id) {
        log.info(String.valueOf(id));
        List<OrderInfoDTO> orderInfoDTOS = orderDAO.getOrders(id);
        log.info(String.valueOf(orderInfoDTOS.get(0)));
        log.info(orderInfoDTOS.toString());
        List<OrderAllInfoDTO> orderAllInfoDTOS = new ArrayList<>();
        for (OrderInfoDTO o:orderInfoDTOS) {
            OrderAllInfoDTO orderAllInfoDTO = new OrderAllInfoDTO();
            orderAllInfoDTO.setUsers_id(o.getUsers_id());
            orderAllInfoDTO.setOrders_id(o.getOrders_id());
            orderAllInfoDTO.setDate(o.getDate());
            orderAllInfoDTO.setOrderedProductsDTOS(orderDAO.getOrderedProducts(o.getOrders_id()));
            orderAllInfoDTO.setTotal(orderAllInfoDTO.getOrderedProductsDTOS().stream().mapToDouble(OrderedProductsDTO::getSum).sum());
            log.info(orderAllInfoDTO.toString());
            orderAllInfoDTOS.add(orderAllInfoDTO);
            log.info(orderAllInfoDTOS.toString());
        }
        log.info(orderAllInfoDTOS.toString());
        return orderAllInfoDTOS;
    }


    @Override
    @Transactional
    public void addOrder(long id) {
        orderDAO.addOrder(id);
    }

}

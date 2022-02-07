package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.OrderDAO;
import by.overone.online_shop.model.Order;
import by.overone.online_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Override
    public void addOrder(long id) {
        orderDAO.addOrder(id);
    }
}

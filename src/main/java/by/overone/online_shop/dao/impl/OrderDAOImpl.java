package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.OrderDAO;
import by.overone.online_shop.dto.CartProductAllInfoDTO;
import by.overone.online_shop.dto.CartProductDTO;
import by.overone.online_shop.dto.OrderedProductsDTO;
import by.overone.online_shop.model.*;
import by.overone.online_shop.service.impl.CartProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderDAOImpl implements OrderDAO {

    private final static String ADD_ORDER = "INSERT INTO orders(date) VALUES(:date)";
    private final static String ADD_ORDER_HAS_USER = "INSERT INTO users_has_orders (orders_id, users_id)  VALUES(?, ?)";
    private final static String ADD_ORDERED_PRODUCTS = "INSERT INTO ordered_products (products_id, name, manufacturer," +
            " price, count, sum) VALUES (:products_id, :name, :manufacturer, :price, :count, :sum)";
    private final static String ADD_ORDERED_PRODUCTS_HAS_ORDERS = "INSERT INTO ordered_products_has_orders " +
            "(ordered_products_id, orders_id) VALUES(?, ?)";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CartProductServiceImpl cartProductService;

    @Override
    @Transactional
    public void addOrder(long id) {

        List<CartProductDTO> cartProducts = cartProductService.getCartProduct(id, null);
        Order order = new Order();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("date", LocalDateTime.now());
        namedParameterJdbcTemplate.update(ADD_ORDER, sqlParameterSource, keyHolder, new String[]{"id"});
        order.setId(keyHolder.getKey().longValue());
        jdbcTemplate.update(ADD_ORDER_HAS_USER, order.getId(), id);
        for (CartProductDTO cartProduct:cartProducts) {
            OrderedProductsDTO orderedProductsDTO = new OrderedProductsDTO();
            SqlParameterSource sqlParameterSource1 = new MapSqlParameterSource()
                    .addValue("products_id", cartProduct.getProducts_id())
                    .addValue("name", cartProduct.getName())
                    .addValue("manufacturer", cartProduct.getManufacturer())
                    .addValue("price", cartProduct.getPrice())
                    .addValue("count", cartProduct.getCarts_products_count())
                    .addValue("sum", cartProduct.getCarts_products_sum());
            namedParameterJdbcTemplate.update(ADD_ORDERED_PRODUCTS, sqlParameterSource1, keyHolder, new String[]{"id"});
            orderedProductsDTO.setProducts_id(keyHolder.getKey().longValue());
            jdbcTemplate.update(ADD_ORDERED_PRODUCTS_HAS_ORDERS, orderedProductsDTO.getProducts_id(), order.getId());
            cartProductService.deleteCartProductByUserId(id);
        }
    }
}


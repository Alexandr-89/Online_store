package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    private final static String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM products";

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = jdbcTemplate.query(GET_ALL_PRODUCTS_QUERY, new BeanPropertyRowMapper<>(Product.class));
        return products;
    }
}

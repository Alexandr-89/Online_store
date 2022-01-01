package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductUpdateForAddDTO;
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
    private final static String GET_ALL_PRODUCT_BY_STATUS_QUERY = "SELECT * FROM products WHERE status=?";
    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM products WHERE id=?";
    private final static String ADD_PRODUCT_QUERY = "INSERT INTO products VALUES(0,?,?,?,?,?)";
    private final static String UPDATE_PRODUCR_BY_COUNT_QUERY = "UPDATE products SET count=?," +
            " status=? WHERE id=?";

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = jdbcTemplate.query(GET_ALL_PRODUCTS_QUERY, new BeanPropertyRowMapper<>(Product.class));
        return products;
    }

    @Override
    public List<Product> getAllProductByStatus(String status) {
        return jdbcTemplate.query(GET_ALL_PRODUCT_BY_STATUS_QUERY,
                new Object[]{status},
                new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public Product getProductById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_QUERY,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        jdbcTemplate.update(ADD_PRODUCT_QUERY,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCount(),
                product.getStatus());

    }

    @Override
    public void updateProductCount(ProductUpdateForAddDTO product) {
        jdbcTemplate.update(UPDATE_PRODUCR_BY_COUNT_QUERY, product.getCount(), product.getStatus(), product.getId());
    }
}

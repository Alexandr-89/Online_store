package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.CartProductDAO;
import by.overone.online_shop.dto.CartProductAllInfoDTO;
import by.overone.online_shop.model.CartProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CartProductDAOImpl implements CartProductDAO {

    private final JdbcTemplate jdbcTemplate;

    private final static String ADD_CART_PRODUCT_QUERY = "INSERT INTO carts_products VALUES(?,?,?,?)";

    private final static String DELETE_CART_PRODUCT_BY_USER_ID_QUERY = "DELETE FROM carts_products WHERE users_id=?";

    private final static String DELETE_CART_PRODUCT_BY_PRODUCT_ID_QUERY = "DELETE FROM carts_products WHERE users_id=?" +
            " AND products_id=?";

    private final static String UPDATE_CART_PRODUCT_BY_COUNT_QUERY = "UPDATE carts_products SET carts_products_count=?," +
            " carts_products_sum=? WHERE users_id=? AND products_id=?";


    @Override
    public void addCartProduct(CartProduct cartProduct) {
        jdbcTemplate.update(ADD_CART_PRODUCT_QUERY,
                cartProduct.getUsers_id(),
                cartProduct.getProducts_id(),
                cartProduct.getCarts_products_count(),
                cartProduct.getCarts_products_sum());
    }

    @Override
    public List<CartProductAllInfoDTO> getCartProduct(Long users_id, Long products_id) {
        String sql = "SELECT *  FROM carts_products JOIN products ON products_id=id";
        if (users_id != null && products_id != null){
            sql = sql + " WHERE users_id = " + users_id + " AND products_id = " +products_id ;
        }
        if (users_id != null && products_id == null) {
            sql = sql + " WHERE users_id = " + users_id;
        }
        if (users_id == null && products_id != null) {
            sql = sql + " WHERE products_id = " + products_id;
        }
        log.info(sql);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartProductAllInfoDTO.class),  new Object[]{});
    }



    @Override
    public void deleteCartProductByUserId(Long users_id) {
        jdbcTemplate.update(DELETE_CART_PRODUCT_BY_USER_ID_QUERY, users_id);
    }

    @Override
    public void deleteCartProduct(Long users_id, Long products_id) {
            jdbcTemplate.update(DELETE_CART_PRODUCT_BY_PRODUCT_ID_QUERY, users_id, products_id);
    }


    @Override
    public void updateProductCount(CartProduct cartProduct) {
        jdbcTemplate.update(UPDATE_CART_PRODUCT_BY_COUNT_QUERY, cartProduct.getCarts_products_count(), cartProduct.getCarts_products_sum(),
                cartProduct.getUsers_id(), cartProduct.getProducts_id());
    }
}

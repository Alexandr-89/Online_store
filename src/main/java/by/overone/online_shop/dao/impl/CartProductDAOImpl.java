package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.CartProductDAO;
import by.overone.online_shop.dto.CartProductAllInfoDTO;
import by.overone.online_shop.model.CartProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartProductDAOImpl implements CartProductDAO {

    private final JdbcTemplate jdbcTemplate;

    private final static String ADD_CART_PRODUCT_QUERY = "INSERT INTO carts_products VALUES(?,?,?,?)";
    private final static String GET_CART_PRODUCT_BY_USER_ID_QUERY = "SELECT * FROM carts_products WHERE users_id=?";
    private final static String GET_CART_PRODUCT_BY_USER_ID_AND_PRODUCT_ID_QUERY = "SELECT * FROM carts_products" +
            " WHERE users_id=? AND products_id=?";
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
            System.out.println(sql + "q");
        }
        if (users_id != null && products_id == null) {
            sql = sql + " WHERE users_id = " + users_id;
            System.out.println(sql + "w");
        }
        if (users_id == null && products_id != null) {
            sql = sql + " WHERE products_id = " + products_id;
            System.out.println(sql +"e");
        }
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(CartProductAllInfoDTO.class));

    }


    @Override
    public List<CartProduct> getCartProductByUserId(long users_id) {
        return jdbcTemplate.query(GET_CART_PRODUCT_BY_USER_ID_QUERY,
                new Object[]{users_id},
                new BeanPropertyRowMapper<>(CartProduct.class));
    }

    @Override
    public CartProduct getCartProductByUserIdAndProductId(long user_id, long products_id) {
        return jdbcTemplate.query(GET_CART_PRODUCT_BY_USER_ID_AND_PRODUCT_ID_QUERY,
                new Object[]{user_id, products_id},
                new BeanPropertyRowMapper<>(CartProduct.class)).stream().findAny().orElse(new CartProduct());
    }

    @Override
    public void deleteCartProductByUserId(long users_id) {
        jdbcTemplate.update(DELETE_CART_PRODUCT_BY_USER_ID_QUERY, users_id);
    }

    @Override
    public void deleteCartProduct(long users_id, long products_id) {
        jdbcTemplate.update(DELETE_CART_PRODUCT_BY_PRODUCT_ID_QUERY, users_id, products_id);
    }


    @Override
    public void updateProductCount(CartProduct cartProduct) {
        System.out.println(4 + cartProduct.toString());
        jdbcTemplate.update(UPDATE_CART_PRODUCT_BY_COUNT_QUERY, cartProduct.getCarts_products_count(), cartProduct.getCarts_products_sum(),
                cartProduct.getUsers_id(), cartProduct.getProducts_id());
    }
}

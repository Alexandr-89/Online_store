package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.dto.ProductUpdateForAddDTO;
import by.overone.online_shop.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;


    private final static String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE id=? AND status = 'active'";

    private final static String ADD_PRODUCT_QUERY = "INSERT INTO products VALUES(0,?,?,?,?,?,?)";

    private final static String UPDATE_PRODUCT_BY_COUNT_QUERY = "UPDATE products SET count=?," +
            " status=? WHERE id=?";

    private final static String DELETE_PRODUCT_QUERY = "UPDATE products SET status='INACTIVE' WHERE id=?";


    @Override
    public Optional<Product> getProductById(Long id) {
        return jdbcTemplate.query(GET_PRODUCT_BY_ID_QUERY,
                new BeanPropertyRowMapper<>(Product.class),
                new Object[]{id}).stream().findAny();
    }


    @Override
    public List<Product> getProduct(ProductForGetDTO product) {
        String sql = "SELECT * FROM products";

        if (product.getName() == null && product.getManufacturer() == null
                && product.getPrice() == 0 && product.getStatus() == null) {
            sql = sql + "  WHERE status = 'active'";
        }
        if (product.getName() != null && product.getManufacturer() == null && product.getPrice() == 0) {
            sql = sql + " WHERE name = '" + product.getName() + "' AND status = 'active'";
        }
        if (product.getName() == null && product.getManufacturer() != null && product.getPrice() == 0) {
            sql = sql + " WHERE manufacturer = '" + product.getManufacturer() + "' AND status = 'active'";
        }
        if (product.getName() != null && product.getManufacturer() != null && product.getPrice() != 0) {
            sql = sql + " WHERE name = '" + product.getName() + "' AND manufacturer = '"+ product.getManufacturer() +
                    "'  AND status = 'active' AND price = '" + product.getPrice() + "'";
        }
        if (product.getName() != null && product.getManufacturer() != null && product.getPrice() == 0) {
            sql = sql + " WHERE name = '" + product.getName() + "' AND manufacturer = '" + product.getManufacturer()
                    +"' AND status = 'active'";
        }
        if (product.getStatus() != null) {
            sql = sql + " WHERE status = '" + product.getStatus() + "'";
        }
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), new Object[]{});
    }



    @Override
    public void addProduct(Product product) {
        jdbcTemplate.update(ADD_PRODUCT_QUERY,
                product.getName(),
                product.getManufacturer(),
                product.getDescription(),
                product.getPrice(),
                product.getCount(),
                product.getStatus());

    }

    @Override
    public void updateProductCount(ProductUpdateForAddDTO product) {
        System.out.println(product);
        jdbcTemplate.update(UPDATE_PRODUCT_BY_COUNT_QUERY, product.getCount(), product.getStatus(), product.getId());
    }

    @Override
    public void deleteProduct(Long id) {
        jdbcTemplate.update(DELETE_PRODUCT_QUERY,  id);
    }
}

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

//    private final static String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM products";
//    private final static String GET_ALL_PRODUCTS_BY_STATUS_QUERY = "SELECT * FROM products WHERE status=?";
    private final static String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE id=?";
    private final static String ADD_PRODUCT_QUERY = "INSERT INTO products VALUES(0,?,?,?,?,?)";
    private final static String UPDATE_PRODUCT_BY_COUNT_QUERY = "UPDATE products SET count=?," +
            " status=? WHERE id=?";
//    private final static String GET_PRODUCT = "SELECT * FROM products WHERE name=? AND description=? AND price=?";

    @Override
    public List<ProductDTO> getProduct(ProductForGetDTO product) {
        String sql = "SELECT * FROM products";

        if (product.getName() == null && product.getManufacturer() == null
                && product.getPrice() == 0 && product.getStatus() == null) {
        }
        if (product.getName() != null && product.getManufacturer() == null) {
            sql = sql + " WHERE name = '" + product.getName() + "'";
            System.out.println(sql);
        }
        if (product.getName() == null && product.getManufacturer() != null) {
            sql = sql + " WHERE manufacturer = '" + product.getManufacturer() + "'";
            System.out.println(sql);
        }
        if (product.getName() != null && product.getManufacturer() != null) {
            sql = sql + " WHERE name = '" + product.getName() + "' AND manufacturer = '" + product.getManufacturer() +"'";
        }
        if (product.getStatus() != null) {
            sql = sql + " WHERE status = '" + product.getStatus() + "'";
        }
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(ProductDTO.class));
    }

//    @Override
//    public List<Product> getAllProduct() {
//        List<Product> products = jdbcTemplate.query(GET_ALL_PRODUCTS_QUERY, new BeanPropertyRowMapper<>(Product.class));
//        return products;
//    }
//
//    @Override
//    public List<Product> getAllProductByStatus(String status) {
//        return jdbcTemplate.query(GET_ALL_PRODUCTS_BY_STATUS_QUERY,
//                new Object[]{status},
//                new BeanPropertyRowMapper<>(Product.class));
//    }

    @Override
    public Optional<Product> getProductById(long id) {
        return jdbcTemplate.query(GET_PRODUCT_BY_ID_QUERY,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Product.class)).stream().findAny();
    }

//    @Override
//    public Product getProduct(String name, String description, double price) {
//        return jdbcTemplate.query(GET_PRODUCT,
//                new Object[]{name, description, price},
//                new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(new Product());
//    }

//    @Override
//    public void addProduct(ProductDTO product) {
//        jdbcTemplate.update(ADD_PRODUCT_QUERY,
//                product.getName(),
//                product.getDescription(),
//                product.getPrice(),
//                product.getCount(),
////                product.getStatus());
//
//    }

    @Override
    public void updateProductCount(ProductUpdateForAddDTO product) {
        jdbcTemplate.update(UPDATE_PRODUCT_BY_COUNT_QUERY, product.getCount(), product.getStatus(), product.getId());
    }
}

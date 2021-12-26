package by.overone.online_shop.dao.mapper;

import by.overone.online_shop.constant.ProductConstant;
import by.overone.online_shop.model.Product;
import by.overone.online_shop.model.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ProductRowMapper implements RowMapper<Product> {


    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong(ProductConstant.ID));
        product.setName(rs.getString(ProductConstant.NAME));
        product.setDescription(rs.getString(ProductConstant.DESCRIPTION));
        product.setPrice(rs.getDouble(ProductConstant.PRICE));
        product.setCount(rs.getLong(ProductConstant.COUNT));
        product.setStatus(Status.valueOf(rs.getString(ProductConstant.STATUS).toUpperCase(Locale.ROOT)));
        return product;
    }
}

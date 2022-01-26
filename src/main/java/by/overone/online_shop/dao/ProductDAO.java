package by.overone.online_shop.dao;

import by.overone.online_shop.model.Product;
import by.overone.online_shop.model.User;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();
    void addProduct(Product product);
}

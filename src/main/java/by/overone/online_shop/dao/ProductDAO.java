package by.overone.online_shop.dao;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductUpdateCountDTO;
import by.overone.online_shop.dto.ProductUpdateForAddDTO;
import by.overone.online_shop.dto.UserUpdateDTO;
import by.overone.online_shop.model.Product;
import by.overone.online_shop.model.User;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProduct();
    List<Product> getAllProductByStatus(String status);
    Product getProductById(long id);
    void addProduct(Product product);
    void updateProductCount(ProductUpdateForAddDTO productUpdateForAddDTO);

}

package by.overone.online_shop.service;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    List<Product> getAllProductsByStatus(String status);
    Product getProductById(long id);
    void addProduct(ProductDTO productDTO);
}

package by.overone.online_shop.dao;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.dto.ProductUpdateForAddDTO;
import by.overone.online_shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    List<Product> getProduct(ProductForGetDTO product);
//    List<Product> getAllProduct();
//    List<Product> getAllProductByStatus(String status);
    Optional<Product> getProductById(long id);
//    Product getProduct(String name, String description, double price);
    void addProduct(Product product);
    void updateProductCount(ProductUpdateForAddDTO productUpdateForAddDTO);

}

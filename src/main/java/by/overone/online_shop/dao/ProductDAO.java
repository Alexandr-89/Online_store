package by.overone.online_shop.dao;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.dto.ProductUpdateForAddDTO;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    List<ProductDTO> getProduct(ProductForGetDTO product);
//    List<Product> getAllProduct();
//    List<Product> getAllProductByStatus(String status);
    Optional<ProductDTO> getProductById(long id);
//    Product getProduct(String name, String description, double price);
    void addProduct(ProductDTO product);
    void updateProductCount(ProductUpdateForAddDTO productUpdateForAddDTO);

}

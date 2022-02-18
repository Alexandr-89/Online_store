package by.overone.online_shop.dao;

import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.dto.ProductUpdateForAddDTO;
import by.overone.online_shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    Optional<Product> getProductById(Long id);
    List<Product> getProduct(ProductForGetDTO product);
    void addProduct(Product product);
    void updateProductCount(ProductUpdateForAddDTO productUpdateForAddDTO);
    void deleteProduct(Long id);

}

package by.overone.online_shop.service;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForGetDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProduct(ProductForGetDTO product);
//    List<Product> getAllProducts();
//    List<Product> getAllProductsByStatus(String status);
    ProductDTO getProductById(long id);
//    Product getProduct(String name, String description, double price);
//    void addProduct(ProductForAddDTO productDTO);
}

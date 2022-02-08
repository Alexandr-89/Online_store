package by.overone.online_shop.service;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForAddDTO;
import by.overone.online_shop.dto.ProductForGetDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProduct(ProductForGetDTO product);
    ProductDTO getProductById(Long id);
    void addProduct(ProductForAddDTO productDTO);
    void deleteProduct(Long id);
}

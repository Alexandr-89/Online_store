package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.model.Product;
import by.overone.online_shop.model.Status;
import by.overone.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productDAO.getAllProducts().stream()
                .map(product -> new Product(product.getId(), product.getName(),product.getProducer(),
                        product.getDescription(), product.getPrice(), product.getCount(), product.getStatus()))
                .collect(Collectors.toList());
        return products;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setProducer(productDTO.getProducer());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCount(productDTO.getCount());
        product.setStatus(Status.ACTIVE);
        productDAO.addProduct(product);
    }
}

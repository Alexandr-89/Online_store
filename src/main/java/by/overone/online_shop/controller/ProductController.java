package by.overone.online_shop.controller;

import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.model.Product;
import by.overone.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> readAll() {
        return productService.getAllProducts();
    }
}

package by.overone.online_shop.controller;

import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.model.Product;
import by.overone.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductDAO productDAO;

    @GetMapping("/all")
    public List<Product> readAll(){
        return productService.getAllProducts();
    }

    @GetMapping("/byStatus")
    public List<Product> getAllProductsByStatus(@RequestParam String status){
        return productService.getAllProductsByStatus(status);
    }

    @GetMapping("/byId")
    public Product getProductById(@RequestParam long id){
        return productService.getProductById(id);
    }


    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
    }


}

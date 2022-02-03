package by.overone.online_shop.controller;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForAddDTO;
import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public List<by.overone.online_shop.dto.ProductDTO> getProduct(@RequestBody ProductForGetDTO product){
        return productService.getProduct(product);
    }


//    @GetMapping("/all")
//    public List<Product> readAll(){
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/byStatus")
//    public List<Product> getAllProductsByStatus(@RequestParam String status){
//        return productService.getAllProductsByStatus(status);
//    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable long id){
        System.out.println(id);
        return productService.getProductById(id);
    }

//    @GetMapping("/product")
//    public Product getProduct(@RequestParam String name, String description, double price){
//         return productService.getProduct(name, description, price);
//    }
//
//
    @PostMapping("/add")
    public void addProduct(@RequestBody ProductForAddDTO productDTO){
        productService.addProduct(productDTO);
    }

}

package by.overone.online_shop.controller;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForAddDTO;
import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }


    @GetMapping
    public List<ProductDTO> getProduct(@RequestBody ProductForGetDTO product){
        return productService.getProduct(product);
    }


    @PostMapping
    public void addProduct(@Validated @RequestBody ProductForAddDTO productDTO){
        System.out.println(productDTO.toString());
        productService.addProduct(productDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}

package by.overone.online_shop.controller;

import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForAddDTO;
import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProduct(@RequestBody ProductForGetDTO product){
        return productService.getProduct(product);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@Validated @RequestBody ProductForAddDTO productDTO){
        System.out.println(productDTO.toString());
        productService.addProduct(productDTO);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

}

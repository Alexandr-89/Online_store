package by.overone.online_shop.controller;

import by.overone.online_shop.dto.CartProductDTO;
import by.overone.online_shop.dto.CartProductForAddDTO;
import by.overone.online_shop.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartProduct")
public class CartProductController {

    private final CartProductService cartProductService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartProductDTO> getCartProduct(@RequestParam Long users_id, Long products_id ){
        return cartProductService.getCartProduct(users_id, products_id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCartProduct(@RequestBody CartProductForAddDTO cartProductForAddDTO){
        cartProductService.addCartProduct(cartProductForAddDTO);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartProductByUserId(@PathVariable("id") Long users_id){
        cartProductService.deleteCartProductByUserId(users_id);
    }


    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public  void deleteCartProductByProductId(@RequestParam  Long users_id, Long products_id){
        cartProductService.deleteCartProduct(users_id, products_id);
    }
}

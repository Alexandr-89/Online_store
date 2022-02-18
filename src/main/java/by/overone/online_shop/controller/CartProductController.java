package by.overone.online_shop.controller;

import by.overone.online_shop.dto.CartProductDTO;
import by.overone.online_shop.dto.CartProductForAddDTO;
import by.overone.online_shop.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartProduct")
public class CartProductController {

    private final CartProductService cartProductService;

    @GetMapping
    public List<CartProductDTO> getCartProduct(@RequestParam @Valid @Min(1) Long users_id, Long products_id ){
        return cartProductService.getCartProduct(users_id, products_id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCartProduct(@RequestBody CartProductForAddDTO cartProductForAddDTO){
        cartProductService.addCartProduct(cartProductForAddDTO);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartProductByUserId(@PathVariable("id") @Valid @Min(1) Long users_id){
        cartProductService.deleteCartProductByUserId(users_id);
    }


    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deleteCartProductByProductId(@RequestParam @Valid @Min(1)  Long users_id, Long products_id){
        cartProductService.deleteCartProduct(users_id, products_id);
    }
}

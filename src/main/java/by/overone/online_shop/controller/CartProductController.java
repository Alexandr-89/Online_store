package by.overone.online_shop.controller;

import by.overone.online_shop.dto.CartProductDTO;
import by.overone.online_shop.model.CartProduct;
import by.overone.online_shop.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartProduct")
public class CartProductController {

    private final CartProductService cartProductService;

    @PostMapping("/add")
    public void addCartProduct(@RequestBody CartProduct cartProduct){
        cartProductService.addCartProduct(cartProduct);
    }

    @GetMapping("/")
    public List<CartProductDTO> getCartProduct(@RequestParam Long users_id, Long products_id ){
        System.out.println(users_id);
        System.out.println(products_id);
        return cartProductService.getCartProduct(users_id, products_id);
    }


    @GetMapping("/byUsersId")
    public List<CartProduct> getCartProductByUsersId(@RequestParam long users_id){
        System.out.println(users_id);
        return cartProductService.getCartProductByUsersId(users_id);
    }

    @DeleteMapping("/deleteByUsersId")
    public void deleteCartProductByUserId(@RequestParam long users_id){
        cartProductService.deleteCartProductByUserId(users_id);
    }

    @DeleteMapping("/delete")
    public  void deleteCartProductByProductId(@RequestParam  long users_id, long products_id){
        cartProductService.deleteCartProduct(users_id, products_id);
    }
}

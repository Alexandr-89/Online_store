package by.overone.online_shop.controller;

import by.overone.online_shop.model.CartProduct;
import by.overone.online_shop.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartProduct")
public class CartProductController {

    private final CartProductService cartProductServise;

    @PostMapping("/add")
    public void addCartProduct(@RequestBody CartProduct cartProduct){
        cartProductServise.addCartProduct(cartProduct);
    }

    @GetMapping("/byUsersId")
    public List<CartProduct> getCartProductByUsersId(@RequestParam long users_id){
        System.out.println(users_id);
        return cartProductServise.getCartProductByUsersId(users_id);
    }

    @DeleteMapping("/deleteByUsersId")
    public void deleteCartProductByUserId(@RequestParam long users_id){
        cartProductServise.deleteCartProductByUserId(users_id);
    }

    @DeleteMapping("/delete")
    public  void deleteCartProductByProductId(@RequestParam  long users_id, long products_id){
        cartProductServise.deleteCartProduct(users_id, products_id);
    }
}

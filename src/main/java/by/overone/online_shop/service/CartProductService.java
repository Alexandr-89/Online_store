package by.overone.online_shop.service;

import by.overone.online_shop.model.CartProduct;

import java.util.List;

public interface CartProductService {

    void addCartProduct(CartProduct cartProduct);
    List<CartProduct> getCartProductByUsersId(long users_id);
    void deleteCartProductByUserId(long users_id);
    void deleteCartProduct(long users_id, long products_id);
}

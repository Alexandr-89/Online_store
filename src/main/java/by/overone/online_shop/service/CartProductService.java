package by.overone.online_shop.service;

import by.overone.online_shop.dao.CartProductDAO;
import by.overone.online_shop.dto.CartProductDTO;
import by.overone.online_shop.model.CartProduct;

import java.util.List;

public interface CartProductService {

    void addCartProduct(CartProduct cartProduct);
    List<CartProductDTO> getCartProduct(Long users_id, Long product_id);
    List<CartProduct> getCartProductByUsersId(long users_id);
    void deleteCartProductByUserId(long users_id);
    void deleteCartProduct(long users_id, long products_id);
}

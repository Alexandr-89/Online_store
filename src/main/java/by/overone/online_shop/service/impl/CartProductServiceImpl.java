package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.CartProductDAO;
import by.overone.online_shop.model.CartProduct;
import by.overone.online_shop.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {

    private final CartProductDAO cartProductDAO;

    @Override
    public void addCartProduct(CartProduct cartProduct) {
        CartProduct cartProducts = cartProductDAO.getCartProductByUserIdAndProductId(cartProduct.getUsers_id(),cartProduct.getProducts_id());
        CartProduct cartProduct1 = new CartProduct();
        if(cartProducts.getUsers_id()!=0){
            cartProduct1.setUsers_id(cartProducts.getUsers_id());
            cartProduct1.setProducts_id(cartProducts.getProducts_id());
            cartProduct1.setCount(cartProduct.getCount()+cartProducts.getCount());
            cartProductDAO.updateProductCount(cartProduct1);
        }else {
            cartProduct1.setUsers_id(cartProduct.getUsers_id());
            cartProduct1.setProducts_id(cartProduct.getProducts_id());
            cartProduct1.setCount(cartProduct.getCount());
            cartProductDAO.addCartProduct(cartProduct1);
        }
    }

    @Override
    public List<CartProduct> getCartProductByUsersId(long users_id) {
        List<CartProduct> cartProducts = cartProductDAO.getCartProductByUserId(users_id).stream()
                .map(cartProduct -> new CartProduct(cartProduct.getUsers_id(), cartProduct.getProducts_id(),
                        cartProduct.getCount())).collect(Collectors.toList());
        return cartProducts;
    }

    @Override
    public void deleteCartProductByUserId(long users_id) {
        cartProductDAO.deleteCartProductByUserId(users_id);
    }

    @Override
    public void deleteCartProduct(long users_id, long products_id) {
        cartProductDAO.deleteCartProduct(users_id, products_id);
    }
}

package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.CartProductDAO;
import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.dto.*;
import by.overone.online_shop.exception.EntityNotFoundException;
import by.overone.online_shop.exception.ExceptionCode;
import by.overone.online_shop.model.CartProduct;
import by.overone.online_shop.model.Product;
import by.overone.online_shop.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {

    private final CartProductDAO cartProductDAO;
    private final ProductDAO productDAO;


    @Override
    public List<CartProductDTO> getCartProduct(Long users_id, Long product_id) {
        List<CartProductDTO> cartProductDTOS = cartProductDAO.getCartProduct(users_id, product_id)
                .stream().map(cartProduct -> new CartProductDTO(cartProduct.getUsers_id(), cartProduct.getProducts_id(),
                        cartProduct.getCarts_products_count(), cartProduct.getCarts_products_sum(), cartProduct.getName(),
                        cartProduct.getManufacturer(),
                        cartProduct.getPrice())).collect(Collectors.toList());
        if (cartProductDTOS.size()!=0){
            return cartProductDTOS;
        }else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_CART_PRODUCT_OR_PRODUCT.getErrorCode());
        }
    }



    @Override
    public void addCartProduct(CartProductForAddDTO cartProductForAddDTO) {
        List<CartProductAllInfoDTO> cartProducts = cartProductDAO.getCartProduct(cartProductForAddDTO.getUsers_id(),cartProductForAddDTO.getProducts_id());
        CartProduct cartProduct1 = new CartProduct();
        if(cartProducts.size()!=0){
            CartProductAllInfoDTO cartProduct2 = cartProducts.get(0);
            cartProduct1.setUsers_id(cartProduct2.getUsers_id());
            cartProduct1.setProducts_id(cartProduct2.getProducts_id());
            cartProduct1.setCarts_products_count(cartProductForAddDTO.getCarts_products_count()+cartProduct2.getCarts_products_count());
            cartProduct1.setCarts_products_sum(cartProduct2.getCarts_products_sum() + (cartProductForAddDTO.getCarts_products_count()*cartProduct2.getPrice()));
            cartProductDAO.updateProductCount(cartProduct1);
        }else {
            Product product = productDAO.getProductById(cartProductForAddDTO.getProducts_id()).orElseThrow(() ->
                    new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PRODUCT.getErrorCode()));
            cartProduct1.setUsers_id(cartProductForAddDTO.getUsers_id());
            cartProduct1.setProducts_id(cartProductForAddDTO.getProducts_id());
            cartProduct1.setCarts_products_count(cartProductForAddDTO.getCarts_products_count());
            cartProduct1.setCarts_products_sum(cartProductForAddDTO.getCarts_products_count()*product.getPrice());
            cartProductDAO.addCartProduct(cartProduct1);
        }
    }



    @Override
    public void deleteCartProductByUserId(long users_id) {
        getCartProduct(users_id, null);
        cartProductDAO.deleteCartProductByUserId(users_id);
    }



    @Override
    public void deleteCartProduct(long users_id, long products_id) {
        getCartProduct(users_id, products_id);
        cartProductDAO.deleteCartProduct(users_id, products_id);
    }
}

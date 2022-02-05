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
    public void addCartProduct(CartProductForAddDTO cartProductForAddDTO) {
        System.out.println(cartProductForAddDTO);
        List<CartProductAllInfoDTO> cartProducts = cartProductDAO.getCartProduct(cartProductForAddDTO.getUsers_id(),cartProductForAddDTO.getProducts_id());
        System.out.println(1+ cartProducts.toString());
        CartProduct cartProduct1 = new CartProduct();
        if(cartProducts.size()!=0){
            CartProductAllInfoDTO cartProduct2 = cartProducts.get(0);
            System.out.println(cartProduct2.toString());
            System.out.println(cartProduct2.toString());
            cartProduct1.setUsers_id(cartProduct2.getUsers_id());
            cartProduct1.setProducts_id(cartProduct2.getProducts_id());
            cartProduct1.setCarts_products_count(cartProductForAddDTO.getCarts_products_count()+cartProduct2.getCarts_products_count());
            cartProduct1.setCarts_products_sum(cartProduct2.getCarts_products_sum() + (cartProductForAddDTO.getCarts_products_count()*cartProduct2.getPrice()));
            System.out.println(2+ cartProduct1.toString());
            cartProductDAO.updateProductCount(cartProduct1);
        }else {
            Product product = productDAO.getProductById(cartProductForAddDTO.getProducts_id()).orElseThrow(() ->
                    new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PRODUCT.getErrorCode()));
            cartProduct1.setUsers_id(cartProductForAddDTO.getUsers_id());
            cartProduct1.setProducts_id(cartProductForAddDTO.getProducts_id());
            cartProduct1.setCarts_products_count(cartProductForAddDTO.getCarts_products_count());
            cartProduct1.setCarts_products_sum(cartProductForAddDTO.getCarts_products_count()*product.getPrice());
            System.out.println(3+ cartProduct1.toString());
            cartProductDAO.addCartProduct(cartProduct1);
        }
    }

    @Override
    public List<CartProductDTO> getCartProduct(Long users_id, Long product_id) {
        System.out.println(cartProductDAO.getCartProduct(users_id, product_id).toString());
        List<CartProductDTO> cartProductDTOS = cartProductDAO.getCartProduct(users_id, product_id)
                .stream().map(cartProduct -> new CartProductDTO(cartProduct.getUsers_id(), cartProduct.getProducts_id(),
                        cartProduct.getCarts_products_count(), cartProduct.getCarts_products_sum(), cartProduct.getName(),
                        cartProduct.getManufacturer(),
                        cartProduct.getPrice())).collect(Collectors.toList());
        if (cartProductDTOS.size()!=0){
            System.out.println(cartProductDTOS);
            return cartProductDTOS;
        }else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
        }
    }


//    @Override
//    public List<CartProduct> getCartProductByUsersId(long users_id) {
//        List<CartProduct> cartProducts = cartProductDAO.getCartProductByUserId(users_id).stream()
//                .map(cartProduct -> new CartProduct(cartProduct.getUsers_id(), cartProduct.getProducts_id(),
//                        cartProduct.getCount())).collect(Collectors.toList());
//        return cartProducts;
//    }

    @Override
    public void deleteCartProductByUserId(long users_id) {
        cartProductDAO.deleteCartProductByUserId(users_id);
    }

    @Override
    public void deleteCartProduct(long users_id, long products_id) {
        cartProductDAO.deleteCartProduct(users_id, products_id);
    }
}

package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.CartProductDAO;
import by.overone.online_shop.dto.CartProductDTO;
import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.exception.EntityNotFoundException;
import by.overone.online_shop.exception.ExceptionCode;
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
        System.out.println(1+ cartProducts.toString());
        CartProduct cartProduct1 = new CartProduct();
        if(cartProducts.getUsers_id()!=0){
            cartProduct1.setUsers_id(cartProducts.getUsers_id());
            cartProduct1.setProducts_id(cartProducts.getProducts_id());
            cartProduct1.setCount(cartProduct.getCount()+cartProducts.getCount());
            System.out.println(2+ cartProduct1.toString());
            cartProductDAO.updateProductCount(cartProduct1);
        }else {
            cartProduct1.setUsers_id(cartProduct.getUsers_id());
            cartProduct1.setProducts_id(cartProduct.getProducts_id());
            cartProduct1.setCount(cartProduct.getCount());
            System.out.println(3+ cartProduct1.toString());
            cartProductDAO.addCartProduct(cartProduct1);
        }
    }

    @Override
    public List<CartProductDTO> getCartProduct(long users_id, long product_id) {
        System.out.println(cartProductDAO.getCartProduct(users_id, product_id).toString());
        List<CartProductDTO> cartProductDTOS = cartProductDAO.getCartProduct(users_id, product_id)
                .stream().map(cartProduct -> new CartProductDTO(cartProduct.getUsers_id(), cartProduct.getProducts_id(),
                        cartProduct.getCart_products_count(), cartProduct.getName(), cartProduct.getManufacturer(),
                        cartProduct.getPrice())).collect(Collectors.toList());
        if (cartProductDTOS.size()!=0){
            System.out.println(cartProductDTOS);
            return cartProductDTOS;
        }else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
        }
    }



//    List<UserDTO> userDTOs = userDAO.findUsers(userForGetDTO)
//            .stream().map(user -> new UserDTO(user.getLogin(), user.getEmail()))
//            .collect(Collectors.toList());
//        if (userDTOs.size()!=0){
//        return userDTOs;
//    }else{
//        throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
//    }

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

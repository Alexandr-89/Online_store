package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.exception.EntityNotFoundException;
import by.overone.online_shop.exception.ExceptionCode;
import by.overone.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

//    @Override
//    public List<Product> getAllProducts() {
//        List<Product> products = productDAO.getAllProduct().stream()
//                .map(product -> new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCount(), product.getStatus()))
//                .collect(Collectors.toList());
//        return products;
//    }

//    @Override
//    public List<Product> getAllProductsByStatus(String status) {
//        List<Product> products = productDAO.getAllProductByStatus(status).stream()
//                .map(product -> new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCount(), product.getStatus()))
//                .collect(Collectors.toList());
//        return products;
//    }

    @Override
    public List<ProductDTO> getProduct(ProductForGetDTO product) {
        System.out.println(product);
        List<ProductDTO> productDTOS = productDAO.getProduct(product)
                .stream().map(product1 -> new ProductDTO(product1.getName(), product1.getManufacturer(),
                        product1.getDescription(), product1.getPrice(), product1.getCount(), product1.getStatus()))
                .collect(Collectors.toList());
        if (productDTOS.size()!=0){
            System.out.println(productDTOS.toString());
            return productDTOS;
        }else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PRODUCT.getErrorCode());
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
    public ProductDTO getProductById(long id) {
        ProductDTO product = new ProductDTO();

        return product;
    }

//     if (id>=1){
//        UserDTO userDTOs = new UserDTO();
//        UserAllDetailsDTO user = userDAO.getUserAllInfoById(id)
//                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
//        userDTOs.setLogin(user.getLogin());
//        userDTOs.setEmail(user.getEmail());
//        return userDTOs;
//    }else {
//        throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
//    }

//    @Override
//    public Product getProduct(String name, String description, double price) {
//         return productDAO.getProduct(name, description, price);
//    }

//    @Override
//    public void addProduct(ProductForAddDTO productDTO) {
//
//         Product product = productDAO.getProduct(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice());
//        System.out.println(product.toString());
//         if (product.getId()!=0){
//             ProductUpdateForAddDTO productUpdateForAddDTO = new ProductUpdateForAddDTO();
//             productUpdateForAddDTO.setCount(product.getCount()+productDTO.getCount());
//             productUpdateForAddDTO.setStatus("ACTIVE");
//             productUpdateForAddDTO.setId(product.getId());
//             productDAO.updateProductCount(productUpdateForAddDTO);
//         }else {
//             product.setName(productDTO.getName());
//             product.setDescription(productDTO.getDescription());
//             product.setPrice(productDTO.getPrice());
//             product.setCount(productDTO.getCount());
//             product.setStatus("ACTIVE");
//             productDAO.addProduct(product);
//         }
//    }
}

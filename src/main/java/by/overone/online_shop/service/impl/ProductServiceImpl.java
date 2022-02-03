package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductForAddDTO;
import by.overone.online_shop.dto.ProductForGetDTO;
import by.overone.online_shop.dto.ProductUpdateForAddDTO;
import by.overone.online_shop.exception.EntityNotFoundException;
import by.overone.online_shop.exception.ExceptionCode;
import by.overone.online_shop.model.Product;
import by.overone.online_shop.model.Status;
import by.overone.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public List<ProductDTO> getProduct(ProductForGetDTO product) {
        System.out.println(product);
        List<ProductDTO> productDTOS = productDAO.getProduct(product)
                .stream().map(product1 -> new ProductDTO(product1.getName(), product1.getManufacturer(),
                        product1.getDescription(), product1.getPrice(), product1.getCount()))
                .collect(Collectors.toList());
        if (productDTOS.size()!=0){
            System.out.println(productDTOS.toString());
            return productDTOS;
        }else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PRODUCT.getErrorCode());
        }
    }


    @Override
    public ProductDTO getProductById(long id) {
        if (id >= 1) {
            ProductDTO productDTOs = new ProductDTO();
            Product product = productDAO.getProductById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PRODUCT.getErrorCode()));
            productDTOs.setName(product.getName());
            productDTOs.setManufacturer(product.getManufacturer());
            productDTOs.setDescription(product.getDescription());
            productDTOs.setPrice(product.getPrice());
            productDTOs.setCount(product.getCount());
            return productDTOs;
        } else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PRODUCT.getErrorCode());
        }
    }


    @Override
    public void addProduct(ProductForAddDTO productForAddDTO) {
        ProductForGetDTO productForGetDTO = new ProductForGetDTO();
        productForGetDTO.setName(productForAddDTO.getName());
        productForGetDTO.setManufacturer(productForAddDTO.getManufacturer());
        productForGetDTO.setPrice(productForAddDTO.getPrice());
         List<Product> products = productDAO.getProduct(productForGetDTO);
        System.out.println(products.toString() + "2");
         if (products.size() != 0){
             Product product1 = products.get(0);
             ProductUpdateForAddDTO productUpdateForAddDTO = new ProductUpdateForAddDTO();
             productUpdateForAddDTO.setCount(product1.getCount()+productForAddDTO.getCount());
             productUpdateForAddDTO.setStatus("ACTIVE");
             productUpdateForAddDTO.setId(product1.getId());
             productDAO.updateProductCount(productUpdateForAddDTO);
         }else {
             Product product1 = new Product();
             product1.setName(productForAddDTO.getName());
             product1.setManufacturer(productForAddDTO.getManufacturer());
             product1.setDescription(productForAddDTO.getDescription());
             product1.setPrice(productForAddDTO.getPrice());
             product1.setCount(productForAddDTO.getCount());
             product1.setStatus(Status.ACTIVE.toString());
             productDAO.addProduct(product1);
         }
    }
}

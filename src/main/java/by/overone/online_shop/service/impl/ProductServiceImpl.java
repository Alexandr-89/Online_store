package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.ProductDAO;
import by.overone.online_shop.dto.ProductDTO;
import by.overone.online_shop.dto.ProductUpdateForAddDTO;
import by.overone.online_shop.model.Product;
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
    public List<Product> getAllProducts() {
        List<Product> products = productDAO.getAllProduct().stream()
                .map(product -> new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCount(), product.getStatus()))
                .collect(Collectors.toList());
        return products;
    }

    @Override
    public List<Product> getAllProductsByStatus(String status) {
        List<Product> products = productDAO.getAllProductByStatus(status).stream()
                .map(product -> new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCount(), product.getStatus()))
                .collect(Collectors.toList());
        return products;
    }

    @Override
    public Product getProductById(long id) {
        Product product = productDAO.getProductById(id);
        return product;
    }

    @Override
    public Product getProduct(String name, String description, double price) {
         return productDAO.getProduct(name, description, price);
    }

    @Override
    public void addProduct(ProductDTO productDTO) {

         Product product = productDAO.getProduct(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice());


//        List<Product> products = productDAO.getAllProduct();
//        Product product = new Product();
//
//        if (products.size()>0) {
//            for (Product product1 : products) {
//                if (productDTO.getName().equals(product1.getName()) && productDTO.getPrice()==product1.getPrice()){
//                    ProductUpdateForAddDTO productUpdateForAddDTO = new ProductUpdateForAddDTO();
//                    productUpdateForAddDTO.setId(product1.getId());
//                    productUpdateForAddDTO.setCount(productDTO.getCount()+product1.getCount());
//                    productUpdateForAddDTO.setStatus("ACTIVE");
//                    productDAO.updateProductCount(productUpdateForAddDTO);
//                }else {
//                    continue;
//                }
//            }
//            }else {
//            product.setName(productDTO.getName());
//            product.setDescription(productDTO.getDescription());
//            product.setPrice(productDTO.getPrice());
//            product.setCount(productDTO.getCount());
//            product.setStatus("ACTIVE");
//            productDAO.addProduct(product);
//        }

//        if (products.size()==0){
//            product.setName(productDTO.getName());
//            product.setDescription(productDTO.getDescription());
//            product.setPrice(productDTO.getPrice());
//            product.setCount(productDTO.getCount());
//            product.setStatus("ACTIVE");
//            productDAO.addProduct(product);
//        }else if (products.size()>0){
//            for (Product product1:products){
//                if (productDTO.getName().equals(product1.getName())){
//                    ProductUpdateForAddDTO productUpdateForAddDTO = new ProductUpdateForAddDTO();
//                    productUpdateForAddDTO.setId(product1.getId());
//                    productUpdateForAddDTO.setCount(productDTO.getCount()+product1.getCount());
//                    productUpdateForAddDTO.setStatus("ACTIVE");
//                    productDAO.updateProductCount(productUpdateForAddDTO);
//                }else {
//                    continue;
//                }
//            }
//        }else {
//            productDAO.addProduct(product);
//        }
    }
}

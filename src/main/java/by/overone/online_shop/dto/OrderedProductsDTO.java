package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductsDTO {

    private long id;
    private long products_id;
    private String name;
    private String manufacturer;
    private double price;
    private long count;
    private double sum;
}

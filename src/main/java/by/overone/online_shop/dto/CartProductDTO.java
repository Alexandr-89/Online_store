package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {

    private long users_id;
    private long products_id;
    private long carts_products_count;
    private double carts_products_sum;
    private String name;
    private String manufacturer;
    private long price;
}

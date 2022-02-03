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
    private long cart_product_count;
    private String name;
    private String manufacturer;
    private long price;
}

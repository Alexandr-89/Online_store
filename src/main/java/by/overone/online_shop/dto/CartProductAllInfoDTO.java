package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductAllInfoDTO {

    private long users_id;
    private long products_id;
    private long cart_products_count;
    private long id;
    private String name;
    private String manufacturer;
    private String description;
    private long price;
    private long count;
    private String status;

}

package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductForAddDTO {
    @Min(1)
    private long users_id;
    @Min(1)
    private long products_id;
    @Min(1)
    private long carts_products_count;
}

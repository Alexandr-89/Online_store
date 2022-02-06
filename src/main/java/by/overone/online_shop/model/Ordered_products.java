package by.overone.online_shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ordered_products {

    @Min(1)
    private long id;
    private String name;
    private String manufacture;
    @Min(1)
    private double price;
    @Min(1)
    private long count;
    @Min(1)
    private double sum;
}

package by.overone.online_shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private long id;
    private String name;
    private String manufacturer;
    private String description;
    private double price;
    private long count;
    private String status;
}

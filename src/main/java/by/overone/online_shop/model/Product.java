package by.overone.online_shop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {

    private long id;
    private String name;
    private String description;
    private double price;
    private long count;
    private String status;
}

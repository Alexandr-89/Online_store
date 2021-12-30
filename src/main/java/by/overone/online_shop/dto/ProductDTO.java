package by.overone.online_shop.dto;

import by.overone.online_shop.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String name;
    private String description;
    private double price;
    private long count;
    private String status;
}

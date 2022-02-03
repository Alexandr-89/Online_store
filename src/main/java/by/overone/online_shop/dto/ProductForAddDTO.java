package by.overone.online_shop.dto;

import by.overone.online_shop.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForAddDTO {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]{2,40}$")
    private String name;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]{2,40}$")
    private String manufacturer;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    private String description;
    @NotEmpty
    @Pattern(regexp = "^[\\d\\D]{1,8}$")
    private double price;
    @NotEmpty
    @Pattern(regexp = "^[\\d]{1,3}$")
    private long count;
}

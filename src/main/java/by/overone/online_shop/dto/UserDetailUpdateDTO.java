package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailUpdateDTO {

    @NotEmpty
    @Pattern(regexp = "^[\\w]{2,30}$")
    private String name;
    @NotEmpty
    @Pattern(regexp = "^[\\w]{2,30}$")
    private String surname;
    @NotEmpty
    @Pattern(regexp = "^[\\w]{5,50}$")
    private String address;
    @NotEmpty
    @Pattern(regexp = "^ +375 ((17 | 29 | 33 | 44)) [0-9] {3} - [0-9] {2} - [0-9] {2} $")
    private String phone;

}

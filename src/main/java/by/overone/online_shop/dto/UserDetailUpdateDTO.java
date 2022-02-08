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


    @Pattern(regexp = "^[\\w]{2,30}$")
    private String name;
    @Pattern(regexp = "^[\\w]{2,30}$")
    private String surname;
    @Pattern(regexp = "^[\\w]{5,50}$")
    private String address;
    @Pattern(regexp = "^[+\\d]{5,50}$")
    private String phone;

}

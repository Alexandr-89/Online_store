package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsForGetDTO {

    @Min(1)
    private long id;
    private String login;
    private String email;
    private String role;
    private String status;
    private String name;
    private String surname;
    private String address;
    private String phone;

}

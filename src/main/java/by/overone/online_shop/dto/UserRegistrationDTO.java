package by.overone.online_shop.dto;

import by.overone.online_shop.model.Role;
import by.overone.online_shop.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotEmpty
    @Pattern(regexp = "^[\\w]{4,12}$")
    private String login;
    @NotEmpty
    @Pattern(regexp = "^[\\w]{8,16}$")
    private String password;
    @NotEmpty
    @Pattern(regexp = "^[\\S]+@[\\w]+\\.[\\a-z]+$")
    private String email;

}

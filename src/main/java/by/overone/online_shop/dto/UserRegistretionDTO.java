package by.overone.online_shop.dto;

import by.overone.online_shop.model.Role;
import by.overone.online_shop.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistretionDTO {

    private String login;
    private String password;
    private String email;
    private Role role;
    private Status status;
}

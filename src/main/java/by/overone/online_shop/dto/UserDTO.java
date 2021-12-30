package by.overone.online_shop.dto;

import by.overone.online_shop.model.Role;
import by.overone.online_shop.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;
    private String login;
    private String email;
    private String role;
    private String status;
}

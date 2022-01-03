package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    private long id;
    private String login;
    private String password;
    private String email;
    private String role;
    private String status;
}

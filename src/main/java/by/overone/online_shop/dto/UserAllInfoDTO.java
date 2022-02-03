package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAllInfoDTO {

    private String login;
    private String email;
//    private String role;
//    private String status;
    private String name;
    private String surname;
    private String address;
    private String phone;

//    public UserAllInfoDTO(String login, String email, String name, String surname, String address, String phone) {
//        this.login = login;
//        this.email = email;
//        this.name = name;
//        this.surname = surname;
//        this.address = address;
//        this.phone = phone;
//    }
}

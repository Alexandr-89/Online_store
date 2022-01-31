package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailUpdateDTO {

    private String name;
    private String surname;
    private String address;
    private String phone;
//    private long users_id;
}

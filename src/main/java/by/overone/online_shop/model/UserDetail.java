package by.overone.online_shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    private String name;
    private String surname;
    private String address;
    private String phone;
    private long users_id;
}

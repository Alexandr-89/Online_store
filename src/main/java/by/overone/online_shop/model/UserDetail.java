package by.overone.online_shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    private String name;
    private String surname;
    private String address;
    private String phone;
    @Min(1)
    private long users_id;
}

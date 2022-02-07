package by.overone.online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO {

    private long users_id;
    private long orders_id;
    private LocalDateTime date;


}

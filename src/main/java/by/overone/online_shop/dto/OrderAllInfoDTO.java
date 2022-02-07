package by.overone.online_shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAllInfoDTO {

    private long users_id;
    private long orders_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private List<OrderedProductsDTO> orderedProductsDTOS;
}

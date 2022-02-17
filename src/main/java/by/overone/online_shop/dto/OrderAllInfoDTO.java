package by.overone.online_shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAllInfoDTO {

    @JsonPropertyOrder({"users_id","orders_id","date","sum","orderedProductsDTOS"})
    private long users_id;
    private long orders_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private double total;

    private List<OrderedProductsDTO> orderedProductsDTOS;
}

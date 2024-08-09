package lk.ijse.pos_system_nike.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    @JsonProperty("odrId")
    private String odrId;

    @JsonProperty("cusId")
    private String cusId;

    @JsonProperty("cusName")
    private String cusName;

    @JsonProperty("date")
    private String date;

    @JsonProperty("orderDetails")
    private List<OrderDetailsDTO> orderDetails;
}

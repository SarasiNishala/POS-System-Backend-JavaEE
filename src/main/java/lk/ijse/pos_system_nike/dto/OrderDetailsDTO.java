package lk.ijse.pos_system_nike.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    private String itemCode;
    private String itemName;
    private String price;
    private String size;
    private String qty;
    private String total;
}

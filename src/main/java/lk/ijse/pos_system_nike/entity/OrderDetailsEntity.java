package lk.ijse.pos_system_nike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsEntity {
    private String itemCode;
    private String itemName;
    private String price;
    private String size;
    private String qty;
    private String total;
}

package lk.ijse.pos_system_nike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemEntity {
    private String itemName;
    private String itemCode;
    private String itmSize;
    private String itemPrice;
    private String itemQTY;
}

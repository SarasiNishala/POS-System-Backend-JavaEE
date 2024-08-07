package lk.ijse.pos_system_nike.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ItemDTO {
    private String itemName;
    private String itemCode;
    private String itmSize;
    private String itemPrice;
    private String itemQTY;
}

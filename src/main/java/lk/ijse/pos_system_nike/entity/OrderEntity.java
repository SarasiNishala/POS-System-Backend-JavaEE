package lk.ijse.pos_system_nike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {
    private String odrId;

    private String cusId;

    private String cusName;

    private String date;
}

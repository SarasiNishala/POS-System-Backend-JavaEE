package lk.ijse.pos_system_nike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEntity {
    private String id;
    private String name;
    private String address;
    private String email;
    private String contact;

}

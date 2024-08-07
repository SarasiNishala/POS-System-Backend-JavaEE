package lk.ijse.pos_system_nike.bo;

import lk.ijse.pos_system_nike.dto.ItemDTO;

import java.sql.Connection;

public interface ItemBo {
    String saveItem(ItemDTO item, Connection connection);
    boolean updateItem(String id, ItemDTO item, Connection connection);
    boolean deleteItem(String id, Connection connection);
    ItemDTO getItem(String id, Connection connection);
}

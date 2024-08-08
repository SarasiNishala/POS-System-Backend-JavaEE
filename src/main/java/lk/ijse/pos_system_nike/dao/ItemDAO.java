package lk.ijse.pos_system_nike.dao;

import lk.ijse.pos_system_nike.dto.ItemDTO;

import java.sql.Connection;

public interface ItemDAO {
    String saveItem(ItemDTO item, Connection connection);
    boolean updateItem(String code, ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String id, Connection connection);
    ItemDTO getItem(String id, Connection connection);
}

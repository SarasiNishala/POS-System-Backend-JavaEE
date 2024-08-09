package lk.ijse.pos_system_nike.bo.custom;

import lk.ijse.pos_system_nike.bo.SuperBO;
import lk.ijse.pos_system_nike.dto.ItemDTO;

import java.sql.Connection;
import java.util.List;

public interface ItemBo extends SuperBO {
    boolean updateItem(ItemDTO dto, Connection connection) throws Exception;
    boolean saveItem(ItemDTO dto, Connection connection) throws Exception;
    List<ItemDTO> getAllItems(Connection connection) throws Exception;
    boolean deleteItem(String code, Connection connection) throws Exception;
}

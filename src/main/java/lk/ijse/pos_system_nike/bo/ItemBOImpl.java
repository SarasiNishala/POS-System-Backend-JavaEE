package lk.ijse.pos_system_nike.bo;

import lk.ijse.pos_system_nike.dao.ItemDAOImpl;
import lk.ijse.pos_system_nike.dto.ItemDTO;

import java.sql.Connection;

public class ItemBOImpl implements ItemBo{

    @Override
    public String saveItem(ItemDTO item, Connection connection) {
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.saveItem(item, connection);
    }

    @Override
    public boolean updateItem(String id, ItemDTO item, Connection connection) {
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.updateItem(id, item, connection);
    }

    @Override
    public boolean deleteItem(String id, Connection connection) {
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.deleteItem(id, connection);
    }

    @Override
    public ItemDTO getItem(String id, Connection connection) {
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.getItem(id, connection);
    }
}

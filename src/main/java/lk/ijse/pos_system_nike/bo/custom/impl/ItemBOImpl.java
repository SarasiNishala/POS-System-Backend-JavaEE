package lk.ijse.pos_system_nike.bo.custom.impl;

import lk.ijse.pos_system_nike.bo.custom.ItemBo;
import lk.ijse.pos_system_nike.dao.DAOFactory;
import lk.ijse.pos_system_nike.dao.custom.ItemDAO;
import lk.ijse.pos_system_nike.dto.ItemDTO;
import lk.ijse.pos_system_nike.entity.ItemEntity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBo {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean updateItem(ItemDTO dto, Connection connection) throws Exception {
        return itemDAO.update(connection,new ItemEntity(
                dto.getItemCode(),
                dto.getItemName(),
                dto.getItmSize(),
                dto.getItemPrice(),
                dto.getItemQTY()));
    }

    @Override
    public boolean saveItem(ItemDTO dto, Connection connection) throws Exception {
        return itemDAO.save(connection,new ItemEntity(
                dto.getItemCode(),
                dto.getItemName(),
                dto.getItmSize(),
                dto.getItemPrice(),
                dto.getItemQTY()));
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws Exception {
        List<ItemEntity> itemEntities = itemDAO.getALL(connection);
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (ItemEntity item : itemEntities){
            itemDTOS.add(new ItemDTO(item.getItemCode(),
                    item.getItemName(),
                    item.getItmSize(),
                    item.getItemPrice(),
                    item.getItemQTY()));
        }
        return itemDTOS;
    }

    @Override
    public boolean deleteItem(String code, Connection connection) throws Exception {
        return itemDAO.delete(connection,code);
    }
}

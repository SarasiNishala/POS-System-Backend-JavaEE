package lk.ijse.pos_system_nike.dao.custom.impl;

import lk.ijse.pos_system_nike.dao.SQLUtil;
import lk.ijse.pos_system_nike.dao.custom.ItemDAO;
import lk.ijse.pos_system_nike.entity.ItemEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean save(Connection connection, ItemEntity entity) throws Exception {
        return SQLUtil.execute( connection,
                "INSERT INTO item VALUES(?, ?, ?, ?,?)",
                entity.getItemCode(),
                entity.getItemName(),
                entity.getItmSize(),
                entity.getItemPrice(),
                entity.getItemQTY()
        );
    }

    @Override
    public boolean update(Connection connection, ItemEntity entity) throws Exception {
        return SQLUtil.execute(connection,
                "UPDATE item SET itemname = ?, size = ?, price = ?, qty = ? WHERE itemCode = ?",
                entity.getItemName(),
                entity.getItmSize(),
                entity.getItemPrice(),
                entity.getItemQTY(),
                entity.getItemCode());
    }

    @Override
    public boolean delete(Connection connection, String code) throws Exception {
        return SQLUtil.execute(connection,
                "DELETE FROM item WHERE itemcode = ?",code);
    }

    @Override
    public List<ItemEntity> getALL(Connection connection) throws Exception {
        List<ItemEntity> items = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(connection,"SELECT * FROM item");
        while(resultSet.next()){
            String itemCode = resultSet.getString(1);
            String itemName = resultSet.getString(2);
            String itemSize = resultSet.getString(3);
            String itemPrice = resultSet.getString(4);
            String itemQTY = resultSet.getString(5);
            var itemEntity = new ItemEntity(itemCode, itemName, itemSize, itemPrice, itemQTY);
            items.add(itemEntity);
        }
        return items;
    }
}

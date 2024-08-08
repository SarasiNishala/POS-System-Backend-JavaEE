package lk.ijse.pos_system_nike.dao;

import lk.ijse.pos_system_nike.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO{

    public static String SAVE_ITEM = "INSERT INTO item (itemname,itemcode,size,price,qty) VALUES (?,?,?,?,?)";
    public static String GET_ITEM = "SELECT * FROM item WHERE itemcode = ?";
    public static String UPDATE_ITEM = "UPDATE item SET itemname = ?, size = ?, price = ?, qty = ? WHERE itemcode = ?";
    public static String DELETE_ITEM = "DELETE FROM item WHERE itemcode = ?";


    @Override
    public String saveItem(ItemDTO item, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_ITEM);
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemCode());
            ps.setString(3, item.getItmSize());
            ps.setString(4, item.getItemPrice());
            ps.setString(5, item.getItemQTY());

            if (ps.executeUpdate() != 0){
                return "Item save successfully";
            }else {
                return "Item save failed";
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItem(String code, ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEM);
            ps.setString(1, itemDTO.getItemName());
            ps.setString(2, itemDTO.getItmSize());
            ps.setString(3, itemDTO.getItemPrice());
            ps.setString(4, itemDTO.getItemQTY());
            ps.setString(5, code);

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String id, Connection connection) {
        try {
            var ps = connection.prepareStatement(DELETE_ITEM);
            ps.setString(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemDTO getItem(String id, Connection connection) {
        try {
            ItemDTO itemDTO = new ItemDTO();

            var ps = connection.prepareStatement(GET_ITEM);
            ps.setString(1, id);
            var rst = ps.executeQuery();

            while (rst.next()){
                itemDTO.setItemName(rst.getString("itemName"));
                itemDTO.setItemCode(rst.getString("itemCode"));
                itemDTO.setItmSize(rst.getString("size"));
                itemDTO.setItemPrice(rst.getString("price"));
                itemDTO.setItemQTY(rst.getString("qty"));
            }

            return itemDTO;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

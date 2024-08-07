package lk.ijse.pos_system_nike.dao;

import lk.ijse.pos_system_nike.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO{

    public static String SAVE_ITEM = "INSERT INTO item (name,code,size,price,qty) VALUES (?,?,?,?,?)";
    public static String GET_ITEM = "SELECT * FROM item WHERE code = ?";
    public static String UPDATE_ITEM = "UPDATE students SET name = ?, size = ?, price = ?, qty = ? WHERE code = ?";
    public static String DELETE_ITEM = "DELETE FROM students WHERE code = ?";


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
    public boolean updateItem(String item, ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEM);
            ps.setString(1, itemDTO.getItemName());
            ps.setString(2, itemDTO.getItmSize());
            ps.setString(3, itemDTO.getItemPrice());
            ps.setString(4, itemDTO.getItemQTY());
            ps.setString(5, item);

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
                itemDTO.setItmSize(rst.getString("itmSize"));
                itemDTO.setItemPrice(rst.getString("itemPrice"));
                itemDTO.setItemQTY(rst.getString("itemQTY"));
            }

            return itemDTO;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

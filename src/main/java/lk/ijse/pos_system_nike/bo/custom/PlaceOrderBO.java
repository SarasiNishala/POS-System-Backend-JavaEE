package lk.ijse.pos_system_nike.bo.custom;

import lk.ijse.pos_system_nike.bo.SuperBO;
import lk.ijse.pos_system_nike.dto.OrderDTO;
import lk.ijse.pos_system_nike.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(OrderDTO orderDTO, Connection connection) throws SQLException;
    List<OrderDetailsDTO> getAllOrders(Connection connection) throws SQLException;
}

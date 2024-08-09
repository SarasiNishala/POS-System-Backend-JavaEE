package lk.ijse.pos_system_nike.dao.custom;

import lk.ijse.pos_system_nike.dao.SupperDAO;
import lk.ijse.pos_system_nike.entity.OrderDetailsEntity;
import lk.ijse.pos_system_nike.entity.OrderEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderDAO extends SupperDAO {
    boolean saveOrder(OrderEntity order, Connection connection) throws SQLException;
    boolean saveOrderDetails(OrderDetailsEntity orderDetails, Connection connection) throws SQLException;
    List<OrderDetailsEntity> getAllOrders(Connection connection) throws SQLException;
}

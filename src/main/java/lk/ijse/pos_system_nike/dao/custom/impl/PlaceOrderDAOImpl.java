package lk.ijse.pos_system_nike.dao.custom.impl;

import lk.ijse.pos_system_nike.dao.SQLUtil;
import lk.ijse.pos_system_nike.dao.custom.PlaceOrderDAO;
import lk.ijse.pos_system_nike.entity.OrderDetailsEntity;
import lk.ijse.pos_system_nike.entity.OrderEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {
    @Override
    public boolean saveOrder(OrderEntity order, Connection connection) throws SQLException {
        String query = "INSERT INTO orders (odrId, cusId, cusName, date) VALUES (?, ?, ?, ?)";
        return SQLUtil.execute(connection, query, order.getOdrId(), order.getCusId(), order.getCusName(),order.getDate());
    }

    @Override
    public boolean saveOrderDetails(OrderDetailsEntity orderDetails, Connection connection) throws SQLException {
        String query = "INSERT INTO orderDetails (itemCode, itemName, price, size, qty, total) VALUES (?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(connection, query, orderDetails.getItemCode(), orderDetails.getItemName(), orderDetails.getPrice(), orderDetails.getSize(), orderDetails.getQty(), orderDetails.getTotal());
    }

    @Override
    public List<OrderDetailsEntity> getAllOrders(Connection connection) throws SQLException {
        List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(connection,"SELECT * FROM orderDetails");
        while (resultSet.next()){
            String itemCode = resultSet.getString(1);
            String itemName = String.valueOf(resultSet.getInt(2));
            String price = String.valueOf(resultSet.getDouble(3));
            String size = resultSet.getString(4);
            String qty = resultSet.getString(5);
            String total = String.valueOf(resultSet.getDouble(6));
            var entity = new OrderDetailsEntity(itemCode, itemName, price, size, qty, total);
            orderDetailsEntities.add(entity);
        }
        return orderDetailsEntities;
    }
}

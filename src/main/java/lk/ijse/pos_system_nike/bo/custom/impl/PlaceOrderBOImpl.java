package lk.ijse.pos_system_nike.bo.custom.impl;

import lk.ijse.pos_system_nike.bo.custom.PlaceOrderBO;
import lk.ijse.pos_system_nike.dao.DAOFactory;
import lk.ijse.pos_system_nike.dao.custom.PlaceOrderDAO;
import lk.ijse.pos_system_nike.dto.OrderDTO;
import lk.ijse.pos_system_nike.dto.OrderDetailsDTO;
import lk.ijse.pos_system_nike.entity.OrderDetailsEntity;
import lk.ijse.pos_system_nike.entity.OrderEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    PlaceOrderDAO placeOrderDAO = (PlaceOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PLACEORDER);
    @Override
    public boolean placeOrder(OrderDTO orderDTO, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);

            OrderEntity order = new OrderEntity();
            order.setOdrId(orderDTO.getOdrId());
            order.setCusId(orderDTO.getCusId());
            order.setCusName(orderDTO.getCusName());
            order.setDate(orderDTO.getDate());

            boolean orderSaved = placeOrderDAO.saveOrder(order, connection);
            if (!orderSaved) {
                connection.rollback();
                return false;
            }

            List<OrderDetailsDTO> orderDetailsList = orderDTO.getOrderDetails();
            for (OrderDetailsDTO detailsDTO : orderDetailsList) {
                OrderDetailsEntity orderDetails = new OrderDetailsEntity();
                orderDetails.setItemCode(detailsDTO.getItemCode());
                orderDetails.setItemName(detailsDTO.getItemName());
                orderDetails.setPrice(detailsDTO.getPrice());
                orderDetails.setSize(detailsDTO.getSize());
                orderDetails.setQty(detailsDTO.getQty());
                orderDetails.setTotal(detailsDTO.getTotal());

                boolean orderDetailsSaved = placeOrderDAO.saveOrderDetails(orderDetails, connection);
                if (!orderDetailsSaved) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<OrderDetailsDTO> getAllOrders(Connection connection) throws SQLException {
        List<OrderDetailsDTO> orderDTOS = new ArrayList<>();
        List<OrderDetailsEntity> orderEntities = placeOrderDAO.getAllOrders(connection);

        for (OrderDetailsEntity entity : orderEntities){
            orderDTOS.add(new OrderDetailsDTO(entity.getItemCode(), entity.getItemName(), entity.getPrice(), entity.getSize(), entity.getQty(), entity.getTotal()));
        }

        return orderDTOS;
    }
}

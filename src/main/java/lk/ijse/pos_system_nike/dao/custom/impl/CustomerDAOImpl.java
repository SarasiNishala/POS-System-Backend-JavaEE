package lk.ijse.pos_system_nike.dao.custom.impl;

import lk.ijse.pos_system_nike.dao.SQLUtil;
import lk.ijse.pos_system_nike.dao.custom.CustomerDAO;
import lk.ijse.pos_system_nike.entity.CustomerEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Connection connection, CustomerEntity entity) throws Exception {
        return SQLUtil.execute( connection,
                "INSERT INTO customer VALUES(?, ?, ?, ?,?)",
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getContact()
        );
    }

    @Override
    public boolean update(Connection connection, CustomerEntity entity) throws Exception {
        return SQLUtil.execute(connection,
                "UPDATE customer SET name = ?, address = ?, email = ?, contact = ? WHERE id = ?",
                entity.getName(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getContact(),
                entity.getId());
    }

    @Override
    public boolean delete(Connection connection, String code) throws Exception {
        return SQLUtil.execute(connection,
                "DELETE FROM customer WHERE itemcode = ?",code);
    }

    @Override
    public List<CustomerEntity> getALL(Connection connection) throws Exception {
        List<CustomerEntity> customers = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(connection,"SELECT * FROM customer");
        while(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String contact = resultSet.getString(5);
            var customerEntitiy = new CustomerEntity(id, name,address,email,contact);
            customers.add(customerEntitiy);
        }
        return customers;
    }
}

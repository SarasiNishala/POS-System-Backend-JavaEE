package lk.ijse.pos_system_nike.bo.custom;

import lk.ijse.pos_system_nike.bo.SuperBO;
import lk.ijse.pos_system_nike.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean updateCustomer(CustomerDTO dto, Connection connection) throws Exception;
    boolean saveCustomer(CustomerDTO dto, Connection connection) throws Exception;
    List<CustomerDTO> getAllCustomers(Connection connection) throws Exception;
    boolean deleteCustomer(String id, Connection connection) throws Exception;
}

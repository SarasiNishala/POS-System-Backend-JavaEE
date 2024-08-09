package lk.ijse.pos_system_nike.bo.custom.impl;

import lk.ijse.pos_system_nike.bo.custom.CustomerBO;
import lk.ijse.pos_system_nike.dao.DAOFactory;
import lk.ijse.pos_system_nike.dao.custom.CustomerDAO;
import lk.ijse.pos_system_nike.dto.CustomerDTO;
import lk.ijse.pos_system_nike.entity.CustomerEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean updateCustomer(CustomerDTO dto, Connection connection) throws Exception {
        return customerDAO.update(connection,new CustomerEntity(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getContact()));
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto, Connection connection) throws Exception {
        return customerDAO.save(connection,new CustomerEntity(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getContact()));
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws Exception {
        List<CustomerEntity> customerEntities = customerDAO.getALL(connection);
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for (CustomerEntity customer : customerEntities) {
            customerDTOS.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getContact()));
        }
        return customerDTOS;
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws Exception {
        return customerDAO.delete(connection,id);
    }
}

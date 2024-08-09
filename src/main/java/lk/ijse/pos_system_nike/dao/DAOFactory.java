package lk.ijse.pos_system_nike.dao;

import lk.ijse.pos_system_nike.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos_system_nike.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos_system_nike.dto.CustomerDTO;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,PLACEORDER
    }

    public SupperDAO getDAO(DAOTypes type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case PLACEORDER:
               //return new PlaceOrderDAOImpl();
            default:
                return null;
        }
    }
}

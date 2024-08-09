package lk.ijse.pos_system_nike.dao;

import java.sql.Connection;
import java.util.List;

public interface CrudDAO<T> extends SupperDAO {
    boolean save(Connection connection, T dto) throws Exception;
    boolean update(Connection connection, T dto) throws Exception;
    boolean delete(Connection connection, String id) throws Exception;
    List <T> getALL(Connection connection) throws Exception;
}

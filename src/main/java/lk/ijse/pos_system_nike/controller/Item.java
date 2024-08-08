package lk.ijse.pos_system_nike.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos_system_nike.bo.ItemBOImpl;
import lk.ijse.pos_system_nike.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item",loadOnStartup = 2)
public class Item extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(Item.class);

    Connection connection;

    @Override
    public void init() throws ServletException {
        logger.info("Init method invoked");

        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/posSystem");

            this.connection = pool.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        //Object binding of the JSON

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();

            ItemBOImpl itemBO = new ItemBOImpl();

            ItemDTO item = jsonb.fromJson(req.getReader(), ItemDTO.class);

            writer.write(itemBO.saveItem(item, connection));
            logger.info("Item saved successfully");
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (JsonbException | IOException e) {
            logger.error("Connection failed");
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer  = resp.getWriter()){

            Jsonb jsonb = JsonbBuilder.create();

            //DB process
            var itemCode = req.getParameter("itemCode");
            ItemBOImpl itemBO = new ItemBOImpl();
            itemBO.getItem(itemCode, connection);

            resp.setContentType("application/json");
            jsonb.toJson(itemBO.getItem(itemCode, connection), writer);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {

            var itemCode = req.getParameter("itemCode");
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO item = jsonb.fromJson(req.getReader(), ItemDTO.class);

            ItemBOImpl itemBO = new ItemBOImpl();

            if (itemBO.updateItem(itemCode, item, connection)) {
                writer.write("Update item successfully");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Update item failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var itemCode = req.getParameter("itemCode");
            ItemBOImpl itemBO = new ItemBOImpl();

            if (itemBO.deleteItem(itemCode, connection)) {
                writer.write("Delete item successfully");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Delete item failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

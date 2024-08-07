package lk.ijse.pos_system_nike.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos_system_nike.dto.ItemDTO;
import lk.ijse.pos_system_nike.util.Util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item",loadOnStartup = 2)
public class Item extends HttpServlet {
    public static String SAVE_ITEM = "INSERT INTO item (itemname,itemcode,size,price,qty) VALUES (?,?,?,?,?)";
    public static String GET_ITEM = "SELECT * FROM item WHERE code = ?";
    public static String UPDATE_ITEM = "UPDATE students SET name = ?, size = ?, price = ?, qty = ? WHERE code = ?";
    public static String DELETE_ITEM = "DELETE FROM students WHERE code = ?";

    Connection connection;

    @Override
    public void init() throws ServletException {

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

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO item = jsonb.fromJson(req.getReader(), ItemDTO.class);
            item.setItemCode(Util.idGenerate());

            //save data to the db

            var ps = connection.prepareStatement(SAVE_ITEM);
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemCode());
            ps.setString(3, item.getItmSize());
            ps.setString(4, item.getItemPrice());
            ps.setString(5, item.getItemQTY());

            if (ps.executeUpdate() != 0){
                resp.setStatus(HttpServletResponse.SC_CREATED);
                System.out.println("Save item successfully");
                writer.write("Save item successfully");
            }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                System.out.println("Save item failed");
                writer.write("Save item failed");
            }

            //create response
            resp.setContentType("application/json");
            jsonb.toJson(item, writer);

        } catch (JsonbException | IOException | SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer  = resp.getWriter()){

            ItemDTO itemDTO = new ItemDTO();
            Jsonb jsonb = JsonbBuilder.create();

            var itemCode = req.getParameter("itemCode");
            var ps = connection.prepareStatement(GET_ITEM);
            ps.setString(1, itemCode);
            var rst = ps.executeQuery();

            while (rst.next()){
                itemDTO.setItemName(rst.getString("name"));
                itemDTO.setItemCode(rst.getString("code"));
                itemDTO.setItmSize(rst.getString("size"));
                itemDTO.setItemPrice(rst.getString("price"));
                itemDTO.setItemQTY(rst.getString("qty"));
            }

            resp.setContentType("application/json");
            jsonb.toJson(itemDTO, writer);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

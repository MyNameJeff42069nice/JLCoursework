package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import server.Main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



@Path("Order/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Order {

    @POST
    @Path("add")
    public String OrdersAdd(@FormDataParam("userID") int userID) {
        System.out.println("Invoked Orders.OrdersAdd()");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Orders (userID, ProcessedState) VALUES (?, ?)");
            ps.setInt(1, userID);
            ps.setInt(2, 1);
            ps.execute();
            return "{\"OK\": \"Added Order.\"}";
        } catch (Exception exception) {
            System.out.println("Database error during /Order/add: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new item - please see console for more information!\"}";
        }
    }

    @GET
    @Path("list/{userID}")
    public String getOrder(@PathParam("userID") int userID) {
        System.out.println("Invoked Order.listOrder() with userID " + userID);
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT OrderID FROM Orders WHERE userID = ?");
            ps.setInt(1, userID);
            ResultSet results = ps.executeQuery();
            JSONArray response = new JSONArray();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("OrderID", results.getInt(1));
                response.add(row);
            }
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item - please see console for more information!\"}";
        }
    }


    @POST
    @Path("addProduct")
    public String ProductOrdersAdd(@FormDataParam("OrderID") Integer OrderID, @FormDataParam("ProductID") Integer ProductID, @FormDataParam("Quantity") Integer Quantity) {
        System.out.println("Invoked Orders.ProductOrdersAdd()");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO ProductOrders (OrderID, ProductID, Quantity) VALUES (?, ?, ?)");
            ps.setInt(1, OrderID);
            ps.setInt(2, ProductID);
            ps.setInt(3, Quantity);
            ps.execute();
            return "{\"OK\": \"Added productOrder.\"}";
        } catch (Exception exception) {
            System.out.println("Database error during /Order/addProduct: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new item - please see console for more information!\"}";
        }
    }
}

package controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("product/")
public class Product {

    @GET
    @Path("list")
    public String productList() {
        System.out.println("Invoked Product.productList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("Select ProductID, ProductName, ProductDepartment, Brand, OtherInfo, Price FROM Products");
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("ProductID", results.getInt(1));
                row.put("ProductName", results.getInt(2));
                row.put("ProductDepartment", results.getInt(3));
                row.put("Brand", results.getInt(4));
                row.put("OtherInfo", results.getInt(5));
                row.put("Price", results.getInt(6));
                response.add(row);
            } // curl command for testing when server fixed: curl -s localhost:8081/food/list
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items. Error code xx.\"}";
        }

    }
}






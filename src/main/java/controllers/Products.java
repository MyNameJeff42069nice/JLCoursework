package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Products/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Products {
    @GET
    @Path("list")
    public String ProductsList() {
        System.out.println("Invoked Products.ProductsList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("Select ProductID, ProductName, ProductDepartment, Brand, OtherInfo, Price FROM Products");
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("ProductID", results.getInt(1));
                row.put("ProductName", results.getString(2));
                row.put("ProductDepartment", results.getString(3));
                row.put("Brand", results.getString(4));
                row.put("OtherInfo", results.getString(5));
                row.put("Price", results.getString(6));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items. Error code xx.\"}";
        }




        // Below are unused as of yet.




    }
    @GET
    @Path("get/{ProductID}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String GetProduct(@PathParam("ProductID") Integer ProductID) {
        System.out.println("Invoked Product.GetProduct() with ProductID " + ProductID);
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ProductName, ProductDepartment, Brand, OtherInfo, Price FROM Products WHERE ProductID = ?");
            ps.setInt(1, ProductID);
            ResultSet results = ps.executeQuery();
            JSONObject response = new JSONObject();
            if (results.next()== true) {
                response.put("ProductID", ProductID);
                response.put("ProductName", results.getString(1));
                response.put("ProductDepartment", results.getString(2));
                response.put("Brand", results.getString(3));
                response.put("OtherInfo", results.getString(4));
                response.put("Price", results.getString(5));
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }
    @POST
    @Path("add")
    public String ProductsAdd(@FormDataParam("ProductName") String ProductName, @FormDataParam("ProductDepartment") String ProductDepartment, @FormDataParam("Brand") String Brand, @FormDataParam("Other Information") String OtherInfo, @FormDataParam("Price") String Price) {
        System.out.println("Invoked Parts.PartsAdd()");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Products (ProductName, ProductDepartment, Brand, OtherInfo, Price) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, ProductName);
            ps.setString(2, ProductDepartment);
            ps.setString(3, Brand);
            ps.setString(4, OtherInfo);
            ps.setString(5, Price);
            ps.execute();
            return "{\"OK\": \"Added Product.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new item, please see server console for more info.\"}";
        }

    }
}






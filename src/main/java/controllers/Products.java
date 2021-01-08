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
    }
    @GET
    @Path("Carplist")
    public String ProductsCarpList() {
        System.out.println("Invoked Products.ProductsCarpList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("Select ProductID, ProductName, ProductDepartment, Brand, OtherInfo, Price FROM Products WHERE ProductDepartment=='Carp'");
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
    }
    @GET
    @Path("Coarselist")
    public String ProductsCoarseList() {
        System.out.println("Invoked Products.ProductsCoarseList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("Select ProductID, ProductName, ProductDepartment, Brand, OtherInfo, Price FROM Products WHERE ProductDepartment=='Coarse'");
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
    }
    @GET
    @Path("Otherlist")
    public String ProductsOtherList() {
        System.out.println("Invoked Products.ProductsOtherList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("Select ProductID, ProductName, ProductDepartment, Brand, OtherInfo, Price FROM Products WHERE ProductDepartment=='Other'");
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
    }
    @GET
    @Path("Predatorlist")
    public String ProductsPredatorList() {
        System.out.println("Invoked Products.ProductsPredatorList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("Select ProductID, ProductName, ProductDepartment, Brand, OtherInfo, Price FROM Products WHERE ProductDepartment=='Predator'");
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
    }
    @GET
    @Path("Sealist")
    public String ProductsSeaList() {
        System.out.println("Invoked Products.ProductsSeaList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("Select ProductID, ProductName, ProductDepartment, Brand, OtherInfo, Price FROM Products WHERE ProductDepartment=='Sea'");
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
    }
}


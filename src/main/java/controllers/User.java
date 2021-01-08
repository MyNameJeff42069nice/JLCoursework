package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import server.Main;
import javax.ws.rs.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@Path("user/")
public class User {

    @POST
    @Path("login")

    public String loginUser(@FormDataParam("username") String username, @FormDataParam("password") String password) {

        System.out.println("Invoked loginUser() on path user/login");
        password = generateHash(password);
        try {
            PreparedStatement ps1 = Main.db.prepareStatement("SELECT password FROM users WHERE username = ?");
            ps1.setString(1, username);
            ResultSet loginResults = ps1.executeQuery();
            if (loginResults.next()) {
                String correctPassword = loginResults.getString(1);
                if (password.equals(correctPassword)) {
                    String token = UUID.randomUUID().toString();
                    PreparedStatement ps2 = Main.db.prepareStatement("UPDATE users SET token = ? WHERE username = ?");
                    ps2.setString(1, token);
                    ps2.setString(2, username);
                    ps2.executeUpdate();

                    JSONObject userDetails = new JSONObject();
                    userDetails.put("username", username);
                    userDetails.put("token", token);
                    return userDetails.toString();
                } else {
                    return "{\"Error\": \"Incorrect username or password!\"}";
                }

            } else {
                return "{\"Error\": \"Unknown username or password!\"}";
            }

        } catch (Exception exception) {
            System.out.println("Database error during /user/login: " + exception.getMessage());
            return "{\"Error\": \"Server side error!\"}";
        }
    }

    public static boolean validToken(String token) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT userID FROM users WHERE token = ?");
            ps.setString(1, token);
            ResultSet logoutResults = ps.executeQuery();
            return logoutResults.next();
        } catch (Exception exception) {
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return false;
        }
    }

    @POST
    @Path("logout/{token}")
    public String logoutUser(@PathParam("token") String token){
        System.out.println("Invoked logoutUser() on path user/logout{token}");
        try{
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET token = null WHERE token = ?");
            ps.setString(1, token);
            ps.execute();
            return "{\"OK\": \"Token deleted\"}";
        } catch (Exception exception){
            System.out.println("Database error during /user/logout/{token}: " + exception.getMessage());
            return "{\"Error\": \"Server side error!\"}";
        }
    }

    @POST
    @Path("add")
    public String UsersAdd(@FormDataParam("email") String email, @FormDataParam("username") String username, @FormDataParam("password") String password) {
        System.out.println("Invoked User.UsersAdd()");
        password = generateHash(password);
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (email, username , password) VALUES (?, ?, ?)");
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.execute();
            return "{\"OK\": \"Added user.\"}";
        } catch (Exception exception) {
            System.out.println("Database error during /user/login: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new item - please see console for more information!\"}";
        }

    }
    public static String generateHash(String text) {
        try {
            MessageDigest hasher = MessageDigest.getInstance("MD5");
            hasher.update(text.getBytes());
            return DatatypeConverter.printHexBinary(hasher.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException nsae) {
            return nsae.getMessage();
        }
    }

}



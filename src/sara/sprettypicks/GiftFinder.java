/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

/**
 *
 * @author sarar
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiftFinder {

    // Method to fetch gift options from the database
    Database db=new Database();
    public List<Product> fetchGiftOptionsFromDatabase(String recipientType, String gender, String ageGroup) {
    List<Product> gifts = new ArrayList<>();

    String sql = "SELECT p.name, p.description, p.price " +
                 "FROM gift_suggestions gs " +
                 "JOIN products p ON gs.product_id = p.product_id " +
                 "WHERE gs.relationship = ? AND gs.gender = ? AND gs.age_group = ?";

    try (PreparedStatement stmt = db.connect().prepareStatement(sql)) {
        stmt.setString(1, recipientType);
        stmt.setString(2, gender);
        stmt.setString(3, ageGroup);
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Product product = new Product(
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price")
            );
            gifts.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace for debugging
    }

    return gifts;
}

    
   


    
}


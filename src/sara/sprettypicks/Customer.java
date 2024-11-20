/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

/**
 *
 * @author sarar
 */
import static com.mysql.cj.telemetry.TelemetryAttribute.DB_USER;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.util.*;



public class Customer implements Observer {
    private String username;
    private DefaultListModel<String> notificationListModel;
    private JList<String> notificationList;

    public Customer(String username, DefaultListModel<String> notificationListModel) {
        this.username = username;
        this.notificationListModel = notificationListModel;
        this.notificationList = new JList<>(notificationListModel); // Associate with the JList
    }

    @Override
    public void update(String message) {
        // Add the new message to the notification list
        notificationListModel.addElement(message);
    }

    // Optional: Method to clear all notifications
    public void clearNotifications() {
        notificationListModel.clear();
    }

    // Getter for the notification JList
    public JList<String> getNotificationList() {
        return notificationList;
    }

    public String getUsername() {
        return username;
    }

}


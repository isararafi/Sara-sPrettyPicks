/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

/**
 *
 * @author sarar
 */
public class SessionManager {
    private static String loggedInUserEmail;
    private static String loggedInUserFirstName; // New field for first name

    public static void setLoggedInUserEmail(String email) {
        loggedInUserEmail = email;
    }

    public static String getLoggedInUserEmail() {
        return loggedInUserEmail;
    }
    
    public static void setLoggedInUserFirstName(String firstName) { // Method to set first name
        loggedInUserFirstName = firstName;
    }

    public static String getLoggedInUserFirstName() { // Method to get first name
        return loggedInUserFirstName;
    }
    
    public static void clearSession() {
        loggedInUserEmail = null;
        loggedInUserFirstName = null; // Clear first name on session clear
    }
}


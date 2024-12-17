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
    private static String loggedInUserName;

    public static void setLoggedInUserEmail(String email) {
        loggedInUserEmail = email;
    }

    public static String getLoggedInUserEmail() {
        return loggedInUserEmail;
    }

    public static void setLoggedInUserName(String username) {
        loggedInUserName = username;
    }

    public static String getLoggedInUserName() {
        return loggedInUserName;
    }
}

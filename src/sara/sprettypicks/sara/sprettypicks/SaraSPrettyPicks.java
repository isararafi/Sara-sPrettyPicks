/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sara.sprettypicks;

/**
 *
 * @author sarars
 */
public class SaraSPrettyPicks {
    //remember to first update info then put anything in cart etc
    //work on showtestcases button
//SwingWorker is used for threading in GUI-based Java programs to keep the UI responsive.
    //admin 1,2,3,4 button done
    //applied singleton and observer
    //****************************************************************************
    /* Subject (Observable): The Admin acts as the subject, responsible for sending notifications. When the admin triggers a notification, the system notifies all registered observers (customers).

                     Observers: The Customer class implements the Observer interface.
                      Each customer registers themselves as an observer to receive notifications.
                      When a notification is sent by the admin, the customers (observers) are notified
                        and their JList (or notification list) is updated with the new message.
                         Notification Flow: When the admin sends a notification, 
                        the notifyObservers() method is called, and all customers in
                        the system are updated with the new message via the update() method, 
                        which adds the message to their notification list model (DefaultListModel).*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new loginformfacade().setVisible(true); // Display the login form
        });
    }

}

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
//admin 1,2,3 button done
    //applied singleton and facade
                    //************************************************************************88
                    /*Single Point of Access: The GUI frames serve as a single point of 
                    access for different functionalities (like browsing products, placing orders, 
                    viewing history), simplifying how the user interacts with the system.*/
                //********************************************************************************
                /*Hiding Complexity: The user doesn't need to know how the database 
                  is queried or how orders are processed—they just interact with the graphical interface. 
                  The complexity is hidden behind these frames, which delegate the tasks to other classes.*/
                    //*****************************************************************************
                    /*in your system, the GUI frames can indeed be considered facade 
                    classes, as they simplify user interaction by providing a clean, easy-to-use interface,
                    while hiding the internal complexities of the system (such as database access, 
                    product browsing, and order management). They serve as an intermediary between
                    the user and the internal logic of the shopping system, 
                    adhering to the Facade Design Pattern.*/
    
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(() -> {
            new loginformfacade().setVisible(true); // Display the login form
        });
    }
    
}

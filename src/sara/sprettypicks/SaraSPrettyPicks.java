 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sara.sprettypicks;

/**
 *
 * @author sarar
 */
public class SaraSPrettyPicks {
//admin 1,2,3 button done(not pushed on github)
    //applied singlwton and facade
    
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(() -> {
            new loginformfacade().setVisible(true); // Display the login form
        });
    }
    
}

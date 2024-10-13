/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author sarar
 */
public class countdownclass {
     private int countdownTime = 300;
    private Timer timer;
    
     public void startCountdown(int discount) {
        // ActionListener to handle timer events every 1 second (1000 milliseconds)
        ActionListener countdownAction = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                countdownTime--;

                if (countdownTime <= 0) {
                    // When the countdown reaches 0, stop the timer and expire the offer
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "The offer has expired! You missed the " + discount + "% discount.");
                } else {
                    // Optionally, update the UI to show remaining time (if you want)
                    int minutes = countdownTime / 60;
                    int seconds = countdownTime % 60;
                    System.out.println("Time left: " + minutes + "m " + seconds + "s");
                }
            }
        };

        // Create a timer that ticks every 1 second (1000 milliseconds)
        timer = new Timer(1000, countdownAction);
        timer.start(); // Start the countdown timer
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuroevolution.avoidingwalls;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class ErrorMessage {
    JFrame errorMessage = new JFrame("Error!");
    JLabel theActualMessage =new JLabel("");
    ErrorMessage(String message) {
        errorMessage.setBounds(0, 0, 200, 100);
        errorMessage.setVisible(true);
        errorMessage.setResizable(false);
        errorMessage.setLayout(null);
        theActualMessage.setBounds(35, 20, 200, 20);
        errorMessage.add(theActualMessage);
        theActualMessage.setText(message);
        
    }
    
}

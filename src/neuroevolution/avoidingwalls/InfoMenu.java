/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuroevolution.avoidingwalls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class InfoMenu {
    JTextArea infoText = new JTextArea();
    JFrame infoMenu = new JFrame("Info");
    JButton back = new JButton("Back");

    InfoMenu() {
        infoMenu.setBounds(0, 0, 500, 350);
        infoMenu.setVisible(true);
        infoMenu.addWindowListener(new CustomWindowCloser());
        infoMenu.setResizable(false);
        infoMenu.setLayout(null);
        back.setBounds(0, 300, 100, 20);
        infoMenu.add(back);
        back.addMouseListener(new backMouseListener());
        infoText.setBounds(0,0,500,300);
        infoText.setEditable(false);
        infoMenu.add(infoText);
        String newline = System.getProperty("line.separator");
        
        infoText.setText("This program lets the user simulate Neiroevoluton, with changeable variables."+ newline 
                + " You can change theese variables in the Options menu (note that the text fields have to"+ newline+ " be filled)"
                + " After you have set the variables in the options menu press Back and then"
                + " press"+ newline+" Start. In the next menu you have to generate the first batch,(this also resets AI porgress)"+newline+" with the Generate new button"
                + " Next you can train the AI "+newline+"(note this might take a while to process), and afterwards view the best AI form the batch!");
    }

    class backMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
 
        @Override
        public void mousePressed(MouseEvent e) {
            new StartMenu();
            infoMenu.dispose();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }
}

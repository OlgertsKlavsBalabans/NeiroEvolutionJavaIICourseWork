package neuroevolution.avoidingwalls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartMenu {
    JFrame startMenu = new JFrame("StartMenu");
    JButton infoMenu = new JButton("Info");
    JButton mainMenu = new JButton ("Start");
    JButton optionMenu = new JButton ("Options");
    StartMenu() {
        startMenu.setBounds(0, 0, 500, 350);
        startMenu.setVisible(true);
        startMenu.addWindowListener(new CustomWindowCloser());
        startMenu.setResizable(false);
        infoMenu.setBounds (150,150,200,20);
        mainMenu.setBounds (150,50,200,20);
        optionMenu.setBounds(150,100,200,20);
        startMenu.add(infoMenu);
        startMenu.add(mainMenu);
        startMenu.add(optionMenu);
        startMenu.setLayout(null);
        infoMenu.addMouseListener(new infoMenuMouseListener());
        optionMenu.addMouseListener(new optionMenuMouseListener());
        mainMenu.addMouseListener(new mainMenuMouseListener());
    }
    
    class mainMenuMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            new MainMenu();
            startMenu.dispose();
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
    class optionMenuMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            new OptionMenu();
            startMenu.dispose();
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
    class infoMenuMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
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

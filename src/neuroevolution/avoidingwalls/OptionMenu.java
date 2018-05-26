package neuroevolution.avoidingwalls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OptionMenu {

    JFrame optionMenu = new JFrame("Options");
    JButton back = new JButton("Back");
    JTextField generationsPerClick = new JTextField("1");
    JLabel generationsPerClickLabel = new JLabel("Generations Per Click"); // do this for the rest
    JTextField aiInstancesPerGeneration = new JTextField("10");
    JTextField maximumGameLength = new JTextField("100");
    JTextField maximumMemoryCells = new JTextField("100");
    JTextField aiSurvivalRate = new JTextField("50");
    JTextField freqvencyOfAddingMemoryCells = new JTextField("4");

    OptionMenu() {
        optionMenu.setBounds(0, 0, 500, 350);
        optionMenu.setVisible(true);
        optionMenu.addWindowListener(new CustomWindowCloser());
        optionMenu.setResizable(false);
        optionMenu.setLayout(null);
        back.setBounds(0, 300, 100, 20);
        optionMenu.add(back);
        aiInstancesPerGeneration.setBounds(100, 50, 50, 20);
        optionMenu.add(aiInstancesPerGeneration);
        maximumGameLength.setBounds(100, 100, 50, 20);
        optionMenu.add(maximumGameLength);
        maximumMemoryCells.setBounds(100, 150, 50, 20);
        optionMenu.add(maximumMemoryCells);
        aiSurvivalRate.setBounds(100, 200, 50, 20);
        optionMenu.add(aiSurvivalRate);
        freqvencyOfAddingMemoryCells.setBounds(100, 250, 50, 20);

        optionMenu.add(freqvencyOfAddingMemoryCells);
        generationsPerClick.setBounds(300, 50, 50, 20);
        optionMenu.add(generationsPerClick);
        back.addMouseListener(new backMouseListener());

    }

    class backMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            String newline = System.getProperty("line.separator");
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("options.txt")));
                writer.write("aiInstancesPerGeneration:"+newline);
                writer.write(aiInstancesPerGeneration.getText()+ newline);
                writer.write("maximumGameLength:"+newline);
                writer.write(maximumGameLength.getText()+ newline);
                writer.write("maximumMemoryCells:"+newline);
                writer.write(maximumMemoryCells.getText()+ newline);
                writer.write("aiSurvivalRate:"+newline);
                writer.write(aiSurvivalRate.getText()+ newline);
                writer.write("freqvencyOfAddingMemoryCells:"+newline);
                writer.write(freqvencyOfAddingMemoryCells.getText()+ newline);
                writer.write("generationsPerClick:"+newline);
                writer.write(generationsPerClick.getText()+ newline);
                writer.close();

            } catch (FileNotFoundException ex) {
                System.out.println("Writing proces in OptionMenu isnt working! (FILE)");
            } catch (IOException ex) {
                System.out.println("Writing proces in OptionMenu isnt working!");
            }
            new StartMenu();
            optionMenu.dispose();

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

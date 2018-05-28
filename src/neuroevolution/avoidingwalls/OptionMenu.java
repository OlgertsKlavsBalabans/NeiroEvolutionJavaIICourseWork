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
    JLabel generationsPerClickLabel = new JLabel("Generations per click:"); // do this for the rest
    JTextField aiInstancesPerGeneration = new JTextField("10");
    JTextField maximumGameLength = new JTextField("100");
    JTextField maximumMemoryCells = new JTextField("100");
    JTextField aiSurvivalRate = new JTextField("50");
    JTextField freqvencyOfAddingMemoryCells = new JTextField("4");
    JLabel aiInstancesPerGenerationLabel = new JLabel("Ai instances per generation:");
    JLabel maximumGameLengthLabel = new JLabel("Maximum game length:");
    JLabel maximumMemoryCellsLabel = new JLabel("MaximumMemoryCells:");
    JLabel aiSurvivalRateLabel = new JLabel("AI survival rate:");
    JLabel freqvencyOfAddingMemoryCellsLabel = new JLabel("Freqvency of adding memory:");
    boolean save = false;
    OptionMenu() {
        optionMenu.setBounds(0, 0, 500, 350);
        optionMenu.setVisible(true);
        optionMenu.addWindowListener(new CustomWindowCloser());
        optionMenu.setResizable(false);
        optionMenu.setLayout(null);
        back.setBounds(0, 300, 100, 20);
        optionMenu.add(back);
        aiInstancesPerGeneration.setBounds(200, 50, 50, 20);
        optionMenu.add(aiInstancesPerGeneration);
        maximumGameLength.setBounds(200, 100, 50, 20);
        optionMenu.add(maximumGameLength);
        maximumMemoryCells.setBounds(200, 150, 50, 20);
        optionMenu.add(maximumMemoryCells);
        aiSurvivalRate.setBounds(200, 200, 50, 20);
        optionMenu.add(aiSurvivalRate);
        freqvencyOfAddingMemoryCells.setBounds(200, 250, 50, 20);

        optionMenu.add(freqvencyOfAddingMemoryCells);
        generationsPerClick.setBounds(400, 50, 50, 20);
        optionMenu.add(generationsPerClick);
        back.addMouseListener(new backMouseListener());
        generationsPerClickLabel.setBounds(250, 50, 200, 20);
        optionMenu.add(generationsPerClickLabel);
        aiInstancesPerGenerationLabel.setBounds(0, 50, 200, 20);
        optionMenu.add(aiInstancesPerGenerationLabel);
        maximumGameLengthLabel.setBounds(0, 100, 200, 20);
        optionMenu.add(maximumGameLengthLabel);
        maximumMemoryCellsLabel.setBounds(0, 150, 200, 20);
        optionMenu.add(maximumMemoryCellsLabel);
        aiSurvivalRateLabel.setBounds(0, 200, 200, 20);
        optionMenu.add(aiSurvivalRateLabel);
        freqvencyOfAddingMemoryCellsLabel.setBounds(0, 250, 200, 20);
        optionMenu.add(freqvencyOfAddingMemoryCellsLabel);
    }

    class backMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            String newline = System.getProperty("line.separator");
            int x;
            try{
            save = true;
            x = Integer.parseInt(aiInstancesPerGeneration.getText());
            if (x<1){save = false;}
            x = Integer.parseInt(maximumGameLength.getText());
            if (x<1){save = false;}
            x = Integer.parseInt(maximumMemoryCells.getText());
            if (x<1){save = false;}
            x = Integer.parseInt(aiSurvivalRate.getText());
            if (x<1){save = false;}
            x = Integer.parseInt(freqvencyOfAddingMemoryCells.getText());
            if (x<1){save = false;}
            x = Integer.parseInt(generationsPerClick.getText());
            if (x<1){save = false;}
            }
            catch(Exception a){
                save = false;
                System.out.println("Wrong option input!");
            }
            
            
            
            if (save == true) {
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("options.txt")));
                writer.write("aiInstancesPerGeneration:" + newline);
                writer.write(aiInstancesPerGeneration.getText() + newline);
                writer.write("maximumGameLength:" + newline);
                writer.write(maximumGameLength.getText() + newline);
                writer.write("maximumMemoryCells:" + newline);
                writer.write(maximumMemoryCells.getText() + newline);
                writer.write("aiSurvivalRate:" + newline);
                writer.write(aiSurvivalRate.getText() + newline);
                writer.write("freqvencyOfAddingMemoryCells:" + newline);
                writer.write(freqvencyOfAddingMemoryCells.getText() + newline);
                writer.write("generationsPerClick:" + newline);
                writer.write(generationsPerClick.getText() + newline);
                writer.close();

            } catch (FileNotFoundException ex) {
                System.out.println("Writing proces in OptionMenu isnt working! (FILE)");
            } catch (IOException ex) {
                System.out.println("Writing proces in OptionMenu isnt working!");
            }
            
            new StartMenu();
            optionMenu.dispose();
            }
            else {
                new ErrorMessage("Input a positive integer");   
            }
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

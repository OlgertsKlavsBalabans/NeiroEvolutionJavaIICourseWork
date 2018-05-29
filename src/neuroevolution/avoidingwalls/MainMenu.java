package neuroevolution.avoidingwalls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu {

    int generationsPerClick; //done
    int aiInstancesPerGeneration;//done
    int maximumGameLength;//done
    int maximumMemoryCells;//done
    int aiSurvivalRate;//done
    int freqvencyOfAddingMemoryCells;//done

    JFrame mainMenu = new JFrame("Main Menu");
    JButton generateButton = new JButton("Start new");
    JButton simulationForXGenerations = new JButton("Simulation for X generations");
    JButton back = new JButton("Back");
    JLabel fitnessLabel = new JLabel("Highest fitness: unknown!");
    JButton visualRepresentationButton = new JButton("Visual representation of best AI");
    int[][][] aiBrain; // brains per generation /memory cells/ each cells instructions
    int[][] aiInput;
    boolean aiLost = false;
    int[][] aiFitness;
    boolean thereIsGeneratedAi = false;
    int[][] bestBrain;
    JLabel doneWithSimulation = new JLabel("");

    MainMenu() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("options.txt"));
            br.readLine();
            aiInstancesPerGeneration = Integer.parseInt(br.readLine());
            br.readLine();
            maximumGameLength = Integer.parseInt(br.readLine());
            br.readLine();
            maximumMemoryCells = Integer.parseInt(br.readLine());
            br.readLine();
            aiSurvivalRate = Integer.parseInt(br.readLine());
            br.readLine();
            freqvencyOfAddingMemoryCells = Integer.parseInt(br.readLine());
            br.readLine();
            generationsPerClick = Integer.parseInt(br.readLine());
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found in MainMenu.java");
        } catch (IOException ex) {
            System.out.println("Problem reading file in MainMenu.java");
        }
        aiBrain = new int[aiInstancesPerGeneration][maximumMemoryCells][4];
        aiInput = new int[10][7];
        aiFitness = new int[aiInstancesPerGeneration][2];
        bestBrain = new int[maximumMemoryCells][4];

        mainMenu.setBounds(0, 0, 500, 350);
        mainMenu.setVisible(true);
        mainMenu.addWindowListener(new CustomWindowCloser());
        mainMenu.setResizable(false);
        mainMenu.add(generateButton);
        generateButton.setBounds(180, 50, 150, 20);
        generateButton.setVisible(true);
        mainMenu.add(simulationForXGenerations);
        simulationForXGenerations.setBounds(125, 100, 250, 20);
        simulationForXGenerations.setVisible(true);
        mainMenu.setLayout(null);
        generateButton.addMouseListener(new GenerateButtonMauseListener());
        simulationForXGenerations.addMouseListener(new SimulationForXGenerationsMouseListener());
        fitnessLabel.setBounds(175, 250, 200, 20);
        fitnessLabel.setVisible(true);
        mainMenu.add(fitnessLabel);
        visualRepresentationButton.setVisible(true);
        visualRepresentationButton.setBounds(140, 150, 225, 20);
        mainMenu.add(visualRepresentationButton);
        visualRepresentationButton.addMouseListener(new visualRepresentationMouseListener());
        back.setBounds(0, 300, 100, 20);
        mainMenu.add(back);
        back.addMouseListener(new backMouseListener());
        simulationForXGenerations.setText("Simulation for " + generationsPerClick + " generations");
        doneWithSimulation.setBounds(200, 125, 200, 20);
        mainMenu.add(doneWithSimulation);
    }

    class backMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            new StartMenu();
            mainMenu.dispose();
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

    class visualRepresentationMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (thereIsGeneratedAi == true) {
                new VisualRepresentation();
                visualRepresentationButton.setVisible(false);
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

    void aiGoesUp() {
        boolean done = false;
        int vertical = 1;
        while (done == false) {
            vertical = vertical + 1;
            if (vertical >= 6) {
                done = true;
            }
            if (aiInput[0][vertical] == 2) {

                aiInput[0][vertical - 1] = aiInput[0][vertical];
                aiInput[0][vertical] = 0;
                done = true;

            }
        }
    }

    void aiGoesDown() {
        boolean done = false;
        int vertical = 0;
        while (done == false) {
            vertical = vertical + 1;
            if (vertical >= 5) {
                done = true;
            }
            if (aiInput[0][vertical] == 2) {

                aiInput[0][vertical + 1] = aiInput[0][vertical];
                aiInput[0][vertical] = 0;
                done = true;

            }
        }
    }

    void aiBrainReactToaiInput() {
        for (int l = 0; l < aiInstancesPerGeneration; l++) { // which brain
            //clear input

            aiInput[0][3] = 2;
            aiLost = false;
            for (int g = 0; g < maximumGameLength; g++) { // which game tick
                if (aiLost == true) {
                    //System.out.println("AI" + l + "Lost!" + aiFitness[l][0]);
                } else {
                    moveAiInputForward();
                    aiInputChange(g);
                    //next frame
                    aiFitness[l][0] = aiFitness[l][0] + 1;
                    aiFitness[l][1] = l;
                    for (int k = 0; k < maximumMemoryCells; k++) { // which memory cell
                        // System.out.println("checking one memory cell");

                        if (aiBrain[l][k][3] > 0) { //checks if empty
                            if (aiInput[aiBrain[l][k][0]][aiBrain[l][k][1]] == aiBrain[l][k][2]) {

                                if (aiBrain[l][k][3] == 1) {//goes up

                                    aiGoesUp();
                                }
                                if (aiBrain[l][k][3] == 2) {//goes down
                                    aiGoesDown();
                                }
                            }
                        }

                    }

                }

            }

        }
    }

    void aiInputChange(int tick) {
        if (tick % 3 == 0) {
            aiInput[9][tick % 7] = 1;
            aiInput[9][(tick + 4) % 7] = 1;

        }
    }

    void moveAiInputForward() {
        for (int i = 0; i < 7; i++) { // does 0-6
            //System.out.println(i); 

            for (int l = 0; l < 10; l++) { // does 0-9
                if (l == 9) { // this if prevents copy from non existing array spot
                    aiInput[l][i] = 0;

                } else {
                    if (aiInput[l][i] == 2) {
                        if (aiInput[l + 1][i] == 1) {
                            aiLost = true;
                        }
                    } else {
                        aiInput[l][i] = aiInput[l + 1][i];
                    }
                }
            }
        }
    }

    class SimulationForXGenerationsMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            doneWithSimulation.setText(" ");
            for (int i = 0; i < generationsPerClick; i++) {

                aiBrainReactToaiInput();
                //System.out.println("Done with simulation!");
                sortAiFitnessScores();
                saveBestAI();
                // System.out.println("Best fitness :" + aiFitness[1][0] + " and it was the " + aiFitness[1][1] + ". AI!");
                fitnessLabel.setText("Highest fitness: " + aiFitness[0][0]);
                makeNewGeneration();

                resetVariablesAndArrays();
                thereIsGeneratedAi = true;
            }
            doneWithSimulation.setText("Done with simulation!");
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

    void saveBestAI() {
        for (int i = 0; i < maximumMemoryCells; i++) {

            for (int l = 0; l < 4; l++) {
                bestBrain[i][l] = aiBrain[aiFitness[0][1]][i][l];
            }

        }
        String newline = System.getProperty("line.separator");

        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("BestAi.txt")));
            for (int j = 0; j < maximumMemoryCells; j++) {
                writer.write("MemoryCell: " + j);
                for (int l = 0; l < 4; l++) {
                    writer.write(" " + bestBrain[j][l]);
                }
                writer.write(newline);
            }

            writer.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Writing proces in MainMenu isnt working! (FILE)");
        } catch (IOException ex) {
            System.out.println("Writing proces in MainMenu isnt working!");
        }

    }

    void resetVariablesAndArrays() {
        aiLost = false;
        for (int[] row : aiInput) {
            Arrays.fill(row, 0);
        }
        for (int[] row : aiFitness) {
            Arrays.fill(row, 0);
        }

    }

    void makeNewGeneration() {
        Random rnd = new Random();
        int hrassness = aiSurvivalRate;
        boolean add = true;
        boolean alreadyChosen = false;
        int firstEmtyCell = rnd.nextInt(maximumMemoryCells);
        int survivors = aiInstancesPerGeneration / (100 / hrassness); // 10 - how many ai In one generation
        if (survivors < 1) {
            survivors = 1;
        }
        for (int i = 0; i < aiInstancesPerGeneration - survivors; i++) {
            alreadyChosen = false;
            if (i % freqvencyOfAddingMemoryCells == 0) {
                add = false;
            } else {
                add = true;
            }
            for (int l = 0; l < maximumMemoryCells; l++) {
                for (int a = 0; a < 4; a++) {

                    aiBrain[aiFitness[survivors + i][1]][l][a] = aiBrain[aiFitness[i % survivors][1]][l][a];
                    if ((aiBrain[aiFitness[survivors + i][1]][l][3] == 0) && (alreadyChosen == false)) {
                        alreadyChosen = true;
                        firstEmtyCell = l;
                       // System.out.println(firstEmtyCell);

                    }
                }
            }
            if (add == true) {
                aiBrain[aiFitness[survivors + i][1]][firstEmtyCell][0] = rnd.nextInt(10);
                aiBrain[aiFitness[survivors + i][1]][firstEmtyCell][1] = rnd.nextInt(7);
                aiBrain[aiFitness[survivors + i][1]][firstEmtyCell][2] = rnd.nextInt(2);
                aiBrain[aiFitness[survivors + i][1]][firstEmtyCell][3] = rnd.nextInt(2) + 1;

            }
            if ((add == false) && (firstEmtyCell > 0)) {
                aiBrain[aiFitness[survivors + i][1]][firstEmtyCell][0] = 0;
                aiBrain[aiFitness[survivors + i][1]][firstEmtyCell][1] = 0;
                aiBrain[aiFitness[survivors + i][1]][firstEmtyCell][2] = 0;
                aiBrain[aiFitness[survivors + i][1]][firstEmtyCell][3] = 0;
            }
        }
    }

    void sortAiFitnessScores() {
        int tempFitness;

        for (int i = 0; i < aiInstancesPerGeneration; i++) {

            for (int l = 0; l < aiInstancesPerGeneration - 1; l++) {

                if (aiFitness[l][0] < aiFitness[l + 1][0]) {
                    tempFitness = aiFitness[l][0];
                    aiFitness[l][0] = aiFitness[l + 1][0];
                    aiFitness[l + 1][0] = tempFitness;
                    tempFitness = aiFitness[l][1];
                    aiFitness[l][1] = aiFitness[l + 1][1];
                    aiFitness[l + 1][1] = tempFitness;
                }
            }
        }
        //  System.out.println("Done with sorting fitness");

    }

    class GenerateButtonMauseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

            Random rnd = new Random();
            resetVariablesAndArrays();
            for (int[][] square : aiBrain) {
                for (int[] line : square) {
                    Arrays.fill(line, 0);
                }
            }
            fitnessLabel.setText("Highest fitness: unknown!");
            for (int i = 0; i < aiInstancesPerGeneration; i++) { // add option for brain to check for 2(player block) 
                aiBrain[i][0][0] = rnd.nextInt(10);
                aiBrain[i][0][1] = rnd.nextInt(7);
                aiBrain[i][0][2] = rnd.nextInt(2);
                aiBrain[i][0][3] = rnd.nextInt(2) + 1;

            }

            // System.out.println("Done generating!");
            thereIsGeneratedAi = false;
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

    public class VisualRepresentation implements Runnable {

        JFrame visualRepresentation = new JFrame("Best AI");
        JButton startVisualRepresentationButton = new JButton("Start");
        JPanel[][] cubeForVisualRepresentation = new JPanel[10][7];

        VisualRepresentation() {
            visualRepresentation.setBounds(0, 0, 500, 375);
            visualRepresentation.setVisible(true);
            visualRepresentation.setResizable(false);
            visualRepresentation.setLayout(null);
            startVisualRepresentationButton.setBounds(200, 150, 100, 20);
            visualRepresentation.add(startVisualRepresentationButton);
            startVisualRepresentationButton.addMouseListener(new startVisualRepresentationMouseListener());

        }

        @Override
        public void run() {

            aiInput[0][3] = 2;
            aiLost = false;
            for (int g = 0; g < maximumGameLength; g++) { // which game tick
                if (aiLost == true) {
                    //System.out.println("AI Lost!");
                    visualRepresentationButton.setVisible(true);
                    break;
                } else {
                    moveAiInputForward();
                    aiInputChange(g);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        //  System.out.println("Visual representation thred interrupted ERROR (in MainMenu.java)");
                    }
                    drawNextFrame();
                    //next frame
                    for (int k = 0; k < maximumMemoryCells; k++) { // which memory cell
                        // System.out.println("checking one memory cell");

                        if (bestBrain[k][3] > 0) { //checks if empty
                            if (aiInput[bestBrain[k][0]][bestBrain[k][1]] == bestBrain[k][2]) {

                                if (bestBrain[k][3] == 1) {//goes up

                                    aiGoesUp();
                                }
                                if (bestBrain[k][3] == 2) {//goes down
                                    aiGoesDown();
                                }
                            }
                        }

                    }

                }

            }
            visualRepresentationButton.setVisible(true);

        }

        class startVisualRepresentationMouseListener implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startVisualRepresentationButton.setVisible(false);
                startVisualRepresentation();
                resetVariablesAndArrays();
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

        void startVisualRepresentation() {
            Thread t = new Thread(this);
            t.start();
        }

        void drawNextFrame() {
            for (int i = 0; i < 10; i++) {

                for (int l = 0; l < 7; l++) {
                    // System.out.println(aiInput[i][l]);
                    // System.out.println("i" + i);
                    //System.out.println("l" + l);
                    if (aiInput[i][l] == 0) {
                        visualRepresentation.add(cubeForVisualRepresentation[i][l] = new JPanel() {

                            @Override
                            public void paint(Graphics g) {
                                g.setColor(Color.white);
                                g.fillRect(0, 0, 50, 50);
                            }
                        }
                        );
                        cubeForVisualRepresentation[i][l].setBounds(50 * i, 50 * l, 50, 50);
                    }
                    if (aiInput[i][l] == 1) {
                        visualRepresentation.add(cubeForVisualRepresentation[i][l] = new JPanel() {

                            @Override
                            public void paint(Graphics g) {
                                g.setColor(Color.red);
                                g.fillRect(0, 0, 50, 50);
                            }
                        }
                        );
                        cubeForVisualRepresentation[i][l].setBounds(50 * i, 50 * l, 50, 50);
                    }
                    if (aiInput[i][l] == 2) {
                        visualRepresentation.add(cubeForVisualRepresentation[i][l] = new JPanel() {

                            @Override
                            public void paint(Graphics g) {
                                g.setColor(Color.black);
                                g.fillRect(0, 0, 50, 50);
                            }
                        }
                        );
                        cubeForVisualRepresentation[i][l].setBounds(50 * i, 50 * l, 50, 50);
                    }
                }
            }

        }

    }

}

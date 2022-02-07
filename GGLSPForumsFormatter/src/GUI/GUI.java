package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import common.CSVReader;
import common.HighscoreReader;
import common.definitions.Skills;
import core.PostFormatter;

public class GUI {
    private JPanel panel1;
    private JButton openButton;
    private JTextArea outputArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GG LSP Highscores Formatter");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public GUI() {
        class Task implements Runnable {
            File file;
            public Task(File file){
                this.file = file;
            }

            @Override
            public void run(){
                try {
                    CSVReader crdr = new CSVReader(file);
                    List<String> skills = Skills.skillNames;
                    PostFormatter formatter = new PostFormatter();

                    for (String[] names : crdr){
                        for (String name : names){
                            String status_text = "Processing XP for player " + name + "; Total Highscores Requests: " + (HighscoreReader.CALLS + 1) + "\n";
                            outputArea.append(status_text);
                            HighscoreReader hrdr = new HighscoreReader(name);
                            HashMap<String, Integer> skillsMap = hrdr.getSkillMap();
                            if (skillsMap.size() == 0) continue;
                            for (String skill : skills){
                                if (skillsMap.get(skill) == 2000000000){
                                    formatter.maxedPlayers.get(skill).add(name);
                                }
                            }
                        }
                    }

                    outputArea.setText(formatter.getFormattedPost());

                } catch (FileNotFoundException e){
                    outputArea.setText("File not found...");
                } catch (IOException e) {
                    outputArea.setText("IO Exception on highscores check, check your network connection");
                }
            }
        }
        // Could be refactored but whatever
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(panel1);

                if (returnVal == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    this.readCSV(file);
                }
            }

            private void readCSV(File file) {
                Thread thread = new Thread(new Task(file));
                thread.start();
            }
        });
    }
}

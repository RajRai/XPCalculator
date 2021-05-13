package gui;

import core.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class GUI {
    private JTextField nameField;
    private JButton searchButton;
    private JPanel lookUpBar;
    private JPanel mainPanel;
    private JComboBox<Skill> skillSelect;
    private JPanel searchBar;
    private JPanel skillSelectPanel;
    private JTable resultsTable;
    private JScrollPane resultsScrollPane;
    private JPanel resultsArea;
    private JPanel expArea;
    private JLabel currExpLabel;
    private JTextField currExp;
    private JTextField goalExp;
    private JLabel goalExpLabel;
    private JButton calculate;
    private JPanel unnecessaryPanel;
    private JPanel leftPane;
    private JPanel rightPane;
    private JCheckBox brawlersBox;
    private JLabel otherBoostsLabel;
    private JTextField xpBoosts;

    private final String[] headers = {"Activity", "Actions", "No. Supply 1", "No. Supply 2", "Location"};
    private double boost = 1.0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Defines custom initialization behavior
    private void createUIComponents() {
        // Set look and feel (theme)
        UIManager.put("control", new Color(60, 63, 65));
        UIManager.put("info", new Color(128, 128, 128));
        UIManager.put("nimbusBase", new Color(18, 30, 49));
        UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
        UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
        UIManager.put("nimbusFocus", new Color(115, 164, 209));
        UIManager.put("nimbusGreen", new Color(176, 179, 50));
        UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
        UIManager.put("nimbusLightBackground", new Color(43, 43, 44));
        UIManager.put("nimbusOrange", new Color(191, 98, 4));
        UIManager.put("nimbusRed", new Color(169, 46, 34));
        UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
        UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
        UIManager.put("text", new Color(230, 230, 230));
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to set the look and feel for the GUI");
        }

        // Add icons to labels
        Map<Object, Icon> icons = new HashMap<>();
        for (Skill skill : Declarations.skills) {
            icons.put(skill.toString(), getIcon(skill.toString()));
        }
        skillSelect = new JComboBox<>(Declarations.skills);
        skillSelect.setRenderer(new IconListRenderer(icons));

        // Initialize empty table
        String[][] data = {};
        resultsTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(data, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
        };
        resultsTable.setModel(tableModel);
    }

    public void applyBoosts() {
        try {
            boost = 1.0 + Double.parseDouble(xpBoosts.getText().replace("%", "")) / 100;
        } catch (Exception exc) {
            boost = 1.0;
        } finally {
            if (brawlersBox.isSelected()) {
                boost += 0.3;
            }
        }
    }


    public GUI() {
        $$$setupUI$$$();

        // Add event listeners
        brawlersBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyBoosts();
            }
        });
        xpBoosts.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                applyBoosts();
            }
        });
        KeyAdapter k = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    super.keyReleased(e);
                    Skill skill = (Skill) skillSelect.getSelectedItem();
                    skill.setCurrExp(Integer.parseInt(currExp.getText()));
                    skill.setGoalExp(Integer.parseInt(goalExp.getText()));
                } catch (Exception ignored) {
                }
            }
        };
        goalExp.addKeyListener(k);
        currExp.addKeyListener(k);

        calculate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Skill skill = (Skill) skillSelect.getSelectedItem();
                int diff = skill.getDiff();
                Object[][] data = new Object[skill.getActivites().length][5];
                Activity[] activites = skill.getActivites();
                for (int i = 0; i < activites.length; i++) {
                    Activity act = activites[i];
                    data[i][0] = act.getDescription();
                    data[i][1] = act.getActionsForXp(diff, boost);
                    Supplies[] supplies = act.suppliesForXp(diff, boost);
                    for (int idx = 0; idx < supplies.length && idx < 2; idx++) {
                        data[i][2 + idx] = supplies[idx].name + ": " + supplies[idx].n;
                    }
                    data[i][4] = act.getLocation();
                    if (!act.getTeleport().equals("")) {
                        data[i][4] += " (Teleport: " + act.getTeleport() + ")";
                    }
                }
                DefaultTableModel model = new DefaultTableModel(data, headers) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        if (columnIndex == 1) return Integer.class;
                        return super.getColumnClass(columnIndex);
                    }
                };
                resultsTable.setModel(model);
            }
        });

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                String name = nameField.getText();
                try {
                    HighscoreReader rdr = new HighscoreReader(name);
                    for (Skill skill : Declarations.skills) {
                        skill.setCurrExp(rdr.getXp(skill.getName()));
                        currExp.setText(Integer.toString(((Skill) skillSelect.getSelectedItem()).getCurrExp()));
                    }
                } catch (Exception exc) {
                    nameField.setText("Error fetching highscores");
                }
            }
        });
        skillSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Skill skill = (Skill) skillSelect.getSelectedItem();
                currExp.setText(Integer.toString(skill.getCurrExp()));
                goalExp.setText(Integer.toString(skill.getGoalExp()));
            }
        });
    }

    private ImageIcon getIcon(String skill) {
        return new ImageIcon(getClass().getResource("/gui/resources/" + skill + "_icon.png"));
    }

    static class IconListRenderer extends DefaultListCellRenderer {
        private final Map<Object, Icon> icons;

        public IconListRenderer(Map<Object, Icon> icons) {
            this.icons = icons;
            setHorizontalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Get icon to use for the list item value
            Icon icon = icons.get(value.toString());

            // Set icon to display for value
            label.setIcon(icon);
            return label;
        }
    }

}

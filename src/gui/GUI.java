package gui;

import core.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
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
        JFrame frame = new JFrame("XPCalculator");
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

    private String formatNumber(int i) {
        return NumberFormat.getNumberInstance(Locale.US).format(i);
    }

    private int parseFormatted(String s) throws NumberFormatException {
        return Integer.parseInt(s.replace(",", ""));
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
                    skill.setCurrExp(parseFormatted(currExp.getText()));
                    skill.setGoalExp(parseFormatted(goalExp.getText()));
                } catch (NumberFormatException ignored) {
                }
            }
        };
        goalExp.addKeyListener(k);
        currExp.addKeyListener(k);

        // Fill table when calculate is pressed
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
                    data[i][1] = formatNumber(act.getActionsForXp(diff, boost));
                    Supplies[] supplies = act.suppliesForXp(diff, boost);
                    for (int idx = 0; idx < supplies.length && idx < 2; idx++) {
                        data[i][2 + idx] = supplies[idx].name + ": " + formatNumber(supplies[idx].n);
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

        // Look-up stats button listener
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                String name = nameField.getText();
                try {
                    HighscoreReader rdr = new HighscoreReader(name);
                    for (Skill skill : Declarations.skills) {
                        skill.setCurrExp(rdr.getXp(skill.getName()));
                        currExp.setText(formatNumber(((Skill) skillSelect.getSelectedItem()).getCurrExp()));
                    }
                } catch (Exception exc) {
                    nameField.setText("Error fetching highscores");
                }
            }
        });
        // Listen to skill select box for changes, update curr exp and goal exp if changed
        skillSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Skill skill = (Skill) skillSelect.getSelectedItem();
                currExp.setText(formatNumber(skill.getCurrExp()));
                goalExp.setText(formatNumber(skill.getGoalExp()));
            }
        });
    }

    private ImageIcon getIcon(String skill) {
        return new ImageIcon(getClass().getResource("/gui/resources/" + skill + "_icon.png"));
    }

    // Custom ComboBox renderer for rendering labels with icons
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        unnecessaryPanel = new JPanel();
        unnecessaryPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(unnecessaryPanel, BorderLayout.CENTER);
        leftPane = new JPanel();
        leftPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftPane.setEnabled(true);
        leftPane.setForeground(new Color(-12828863));
        unnecessaryPanel.add(leftPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        resultsArea = new JPanel();
        resultsArea.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftPane.add(resultsArea, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resultsScrollPane = new JScrollPane();
        resultsArea.add(resultsScrollPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resultsTable.setAutoCreateRowSorter(true);
        resultsTable.setAutoResizeMode(4);
        resultsTable.setFillsViewportHeight(true);
        resultsTable.setSelectionBackground(new Color(-11833681));
        resultsScrollPane.setViewportView(resultsTable);
        lookUpBar = new JPanel();
        lookUpBar.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftPane.add(lookUpBar, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Look-up Stats:");
        lookUpBar.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        searchBar = new JPanel();
        searchBar.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        lookUpBar.add(searchBar, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        searchBar.add(searchButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        nameField = new JTextField();
        searchBar.add(nameField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(150, -1), null, 0, false));
        rightPane = new JPanel();
        rightPane.setLayout(new BorderLayout(0, 0));
        unnecessaryPanel.add(rightPane, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        skillSelectPanel = new JPanel();
        skillSelectPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rightPane.add(skillSelectPanel, BorderLayout.NORTH);
        final JLabel label2 = new JLabel();
        label2.setText("Skill Selection");
        skillSelectPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        skillSelectPanel.add(skillSelect, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        expArea = new JPanel();
        expArea.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        rightPane.add(expArea, BorderLayout.CENTER);
        currExpLabel = new JLabel();
        currExpLabel.setText("Current Experience:");
        expArea.add(currExpLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currExp = new JTextField();
        expArea.add(currExp, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        goalExpLabel = new JLabel();
        goalExpLabel.setText("Goal Experience:");
        expArea.add(goalExpLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        goalExp = new JTextField();
        expArea.add(goalExp, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        expArea.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        calculate = new JButton();
        calculate.setText("Calculate");
        expArea.add(calculate, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        brawlersBox = new JCheckBox();
        brawlersBox.setText("Brawling Gloves?");
        expArea.add(brawlersBox, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        otherBoostsLabel = new JLabel();
        otherBoostsLabel.setText("Other XP Boosts (%)");
        expArea.add(otherBoostsLabel, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        xpBoosts = new JTextField();
        expArea.add(xpBoosts, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}

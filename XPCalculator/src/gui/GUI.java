package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import core.*;
import common.HighscoreReader;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.StyleContext;
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
    private JPanel tableArea;
    private JPanel expArea;
    private JLabel currExpLabel;
    private JTextField currExp;
    private JTextField goalExp;
    private JLabel goalExpLabel;
    private JButton calculate;
    private JPanel gridPanel;
    private JPanel leftPane;
    private JCheckBox brawlersBox;
    private JLabel otherBoostsLabel;
    private JTextField xpBoosts;
    private JPanel rightPaneCenter;
    private JPanel rightPane;
    private JPanel suppliesArea;
    private JPanel[] supplyPanels;
    private JTextField[] supplyFields;
    private JLabel[] supplyLabels;
    private JLabel[] supplyTypes;

    private final String[] headers = {"Activity", "Actions", "No. Supply 1", "No. Supply 2", "Location"};
    private double boost = 1.0;
    private int numSuppliesColumns = 2;

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

        // Generate supply panels
        suppliesArea = new JPanel();
        suppliesArea.setLayout(new GridLayoutManager(numSuppliesColumns + 1, 1, new Insets(0, 0, 0, 0), -1, -1));

        supplyPanels = new JPanel[numSuppliesColumns];
        supplyFields = new JTextField[numSuppliesColumns];
        supplyLabels = new JLabel[numSuppliesColumns];
        supplyTypes = new JLabel[numSuppliesColumns];
        for (int i = 0; i < numSuppliesColumns; i++) {
            supplyPanels[i] = new JPanel();
            supplyPanels[i].setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
            suppliesArea.add(supplyPanels[i], new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
            supplyTypes[i] = new JLabel();
            supplyTypes[i].setText("Supply " + (i + 1) + ":");
            supplyPanels[i].add(supplyTypes[i], new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            supplyFields[i] = new JTextField();
            supplyPanels[i].add(supplyFields[i], new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
            final JLabel label4 = new JLabel();
            label4.setText("Owned:");
            supplyPanels[i].add(label4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            final JLabel label5 = new JLabel();
            label5.setText("Remaining:");
            supplyPanels[i].add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            supplyLabels[i] = new JLabel();
            supplyLabels[i].setText("0");
            supplyPanels[i].add(supplyLabels[i], new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            suppliesArea.add(supplyPanels[i], new GridConstraints(i, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        }
        final Spacer spacer1 = new Spacer();
        suppliesArea.add(spacer1, new GridConstraints(numSuppliesColumns, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 10), null, new Dimension(-1, 20), 0, false));
    }

    private void applyBoosts() {
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

    private void tableAndSuppliesListener() {
        Skill skill = (Skill) skillSelect.getSelectedItem();
        int row = resultsTable.getSelectedRow();
        Activity act = ((ActivityTableModel) resultsTable.getModel()).getRow(row);
        for (int i = 0; i < numSuppliesColumns; i++) {
            int owned = 0;
            if (!supplyFields[i].getText().equals("")) {
                owned = parseFormatted(supplyFields[i].getText());
            }
            int needed = 0;
            try {
                needed = act.getSupplies()[i].n * act.getActionsForXp(skill.getDiff(), boost);
                skill.getActivites()[row].getSupplies()[i].owned = owned;
                supplyTypes[i].setText(act.getSupplies()[i].name);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            supplyLabels[i].setText(formatNumber(needed - owned));
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
        KeyAdapter parseExp = new KeyAdapter() {
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
        goalExp.addKeyListener(parseExp);
        currExp.addKeyListener(parseExp);


        KeyAdapter parseSupplies = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                tableAndSuppliesListener();
            }
        };
        for (int i = 0; i < numSuppliesColumns; i++) {
            supplyFields[i].addKeyListener(parseSupplies);
        }

        resultsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int row = resultsTable.getSelectedRow();
                Activity act = ((ActivityTableModel) resultsTable.getModel()).getRow(row);
                for (int i = 0; i < numSuppliesColumns && i < act.getSupplies().length; i++) {
                    supplyFields[i].setText(formatNumber(act.getSupplies()[i].owned));
                }
                tableAndSuppliesListener();
            }
        });

        // Fill table when calculate is pressed. Also format the exp boxes
        calculate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Skill skill = (Skill) skillSelect.getSelectedItem();
                Activity[] activities = skill.getActivites();
                AbstractTableModel model = new ActivityTableModel(activities, headers, numSuppliesColumns);
                resultsTable.setModel(model);
                currExp.setText(formatNumber(parseFormatted(currExp.getText())));
                goalExp.setText(formatNumber(parseFormatted(goalExp.getText())));
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

    // Custom JTable Model for storing and displaying Activity objects
    class ActivityTableModel extends AbstractTableModel {
        private Activity[] data;
        private String[] headers;
        private int numSuppliesColumns;

        public ActivityTableModel(Activity[] data, String[] headers, int numSuppliesColumns) {
            this.data = data;
            this.headers = headers;
            this.numSuppliesColumns = numSuppliesColumns;
        }

        public Activity getRow(int row) {
            return data[row];
        }

        public int getColumnCount() {
            return 3 + numSuppliesColumns;
        }

        public int getRowCount() {
            return data.length;
        }

        public Object getValueAt(int row, int col) {
            Object result = null;
            Skill skill = (Skill) skillSelect.getSelectedItem();
            int diff = skill.getDiff();
            int actions = data[row].getActionsForXp(diff, boost);
            if (col == 0) {
                result = data[row].getDescription();
            } else if (col == 1) {
                result = formatNumber(actions);
            } else if (col < 2 + numSuppliesColumns) {
                Supplies[] supplies = data[row].getSupplies();
                if (supplies == null) {
                    result = "";
                } else {
                    if (col - 2 < supplies.length) {
                        result = supplies[col - 2].name + ": " + formatNumber(actions * supplies[col - 2].n);
                    }
                }
            } else if (col < 3 + numSuppliesColumns) {
                result = data[row].getLocation();
                if (!data[row].getTeleport().equals("")) {
                    result += " (Teleport: " + data[row].getTeleport() + ")";
                }
            }
            return result;
        }

        public String getColumnName(int col) {
            return headers[col];
        }

        public boolean isCellEditable(int row, int column) {
            return false;
        }

        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 1) return Integer.class;
            return super.getColumnClass(columnIndex);
        }
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
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        leftPane = new JPanel();
        leftPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftPane.setEnabled(true);
        leftPane.setForeground(new Color(-12828863));
        gridPanel.add(leftPane, new GridConstraints(0, 0, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tableArea = new JPanel();
        tableArea.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftPane.add(tableArea, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resultsScrollPane = new JScrollPane();
        tableArea.add(resultsScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resultsTable = new JTable();
        resultsTable.setAutoCreateRowSorter(true);
        resultsTable.setAutoResizeMode(4);
        resultsTable.setFillsViewportHeight(true);
        resultsTable.setSelectionBackground(new Color(-11833681));
        resultsScrollPane.setViewportView(resultsTable);
        lookUpBar = new JPanel();
        lookUpBar.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftPane.add(lookUpBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Look-up Stats:");
        lookUpBar.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        searchBar = new JPanel();
        searchBar.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        lookUpBar.add(searchBar, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        searchBar.add(searchButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        nameField = new JTextField();
        searchBar.add(nameField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(150, -1), null, 0, false));
        rightPane = new JPanel();
        rightPane.setLayout(new BorderLayout(0, 0));
        gridPanel.add(rightPane, new GridConstraints(0, 1, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rightPaneCenter = new JPanel();
        rightPaneCenter.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Font rightPaneCenterFont = this.$$$getFont$$$(null, -1, -1, rightPaneCenter.getFont());
        if (rightPaneCenterFont != null) rightPaneCenter.setFont(rightPaneCenterFont);
        rightPane.add(rightPaneCenter, BorderLayout.CENTER);
        expArea = new JPanel();
        expArea.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        rightPaneCenter.add(expArea, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        currExpLabel = new JLabel();
        currExpLabel.setText("Current Experience:");
        expArea.add(currExpLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currExp = new JTextField();
        expArea.add(currExp, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        goalExpLabel = new JLabel();
        goalExpLabel.setText("Goal Experience:");
        expArea.add(goalExpLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        goalExp = new JTextField();
        expArea.add(goalExp, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        calculate = new JButton();
        calculate.setText("Calculate");
        expArea.add(calculate, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        brawlersBox = new JCheckBox();
        brawlersBox.setText("Brawling Gloves?");
        expArea.add(brawlersBox, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        otherBoostsLabel = new JLabel();
        otherBoostsLabel.setText("Other XP Boosts (%)");
        expArea.add(otherBoostsLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        xpBoosts = new JTextField();
        expArea.add(xpBoosts, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        expArea.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        expArea.add(spacer2, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 50), null, null, 0, false));
        skillSelectPanel = new JPanel();
        skillSelectPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rightPane.add(skillSelectPanel, BorderLayout.NORTH);
        skillSelectPanel.add(skillSelect, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Skill Selection");
        skillSelectPanel.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        rightPane.add(suppliesArea, BorderLayout.SOUTH);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}

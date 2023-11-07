import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDatabaseAppGUI {
    private JButton openServiceAdderButton; // Button to open the service adder
    private JButton openInsertFormButton; // Button to open the pet insertion form
    private DefaultTableModel tableModel;
    private JFrame mainFrame;
    private JComboBox<String> petComboBox;
    private JButton searchButton;
 
    private JComboBox<Integer> visitIdComboBox; // Dropdown for Visit ID
    private JButton getTotalCostButton; // Button to execute GetTotalCost

    public PetDatabaseAppGUI() {
        initializeUI();
    }

    private void openInsertForm() {
        addPet insertionForm = new addPet();
        insertionForm.setVisible(true);
    }
    private void openServiceAdder() {
        ServiceAdder serviceAdderForm = new ServiceAdder();
        serviceAdderForm.setVisible(true);
    }

    private void initializeUI() {
        mainFrame = new JFrame("Pet Database App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());

        // Create a combo box to select pets
        JLabel petLabel = new JLabel("Pet:");
        mainFrame.add(petLabel);
        petComboBox = new JComboBox<>();
        populatePetComboBox();
        mainFrame.add(petComboBox);

        // Create a search button
        searchButton = new JButton("Search");
        mainFrame.add(searchButton);

        // Create a dropdown for Visit ID
        JLabel visitIdLabel = new JLabel("Visit ID:");
        mainFrame.add(visitIdLabel);
        visitIdComboBox = new JComboBox<>();
        populateVisitIdComboBox();
        mainFrame.add(visitIdComboBox);

        // Create a button to execute GetTotalCost
        getTotalCostButton = new JButton("Get Total Cost");
        mainFrame.add(getTotalCostButton);

        openInsertFormButton = new JButton("Add Pet");
        mainFrame.add(openInsertFormButton);

        openServiceAdderButton = new JButton("Add Service to Pet");
        mainFrame.add(openServiceAdderButton);

        JTable table = new JTable();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        // does lets the words run of the table and adjust each column to the size of the word
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        mainFrame.add(scrollPane);

        

        // addServiceButton = new JButton("Add Service to Pet");
        // mainFrame.add(addServiceButton);

        openServiceAdderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openServiceAdder();
            }
        });

        openInsertFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            openInsertForm();
            }
        });


        // Add action listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchVisitDetails();
            }
        });

        getTotalCostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeGetTotalCost();
            }
        });

        mainFrame.setSize(600, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void populatePetComboBox() {
        // Connect to the database and populate the combo box with pet names
        try (Connection connection = DriverManager.getConnection(";")) {
            String sql = "SELECT Name FROM Pets";
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    petComboBox.addItem(resultSet.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Problem:\n" + ex.getMessage());
        }
    }

    private void populateVisitIdComboBox() {
        // Connect to the database and populate the Visit ID dropdown with visit IDs
        try (Connection connection = DriverManager.getConnection("")) {
            String sql = "SELECT VisitID FROM VisitDetails";
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    visitIdComboBox.addItem(resultSet.getInt("VisitID"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Problem:\n" + ex.getMessage());
        }
    }

    private void searchVisitDetails() {
        String selectedPetName = petComboBox.getSelectedItem().toString();
        try (Connection connection = DriverManager.getConnection("")) {
            String sql = "SELECT * FROM VisitDetails WHERE PetName = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, selectedPetName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Clear existing data
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(0);
                    // Add column headers
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        tableModel.addColumn(metaData.getColumnName(i));
                    }
                    // Add rows
                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        tableModel.addRow(row);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Query Problem:\n" + ex.getMessage());
        }
    }
    
    private void executeGetTotalCost() {
        int selectedVisitID = (int) visitIdComboBox.getSelectedItem();
        try (Connection connection = DriverManager.getConnection("")) {
            String sql = "{call GetTotalCost(?)}";
            try (CallableStatement callableStatement = connection.prepareCall(sql)) {
                callableStatement.setInt(1, selectedVisitID);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    // Clear existing data
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(0);
                    // Add column headers
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        tableModel.addColumn(metaData.getColumnName(i));
                    }
                    // Add rows
                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        tableModel.addRow(row);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Query Problem:\n" + ex.getMessage());
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                new PetDatabaseAppGUI();
            }
        });
    }
}

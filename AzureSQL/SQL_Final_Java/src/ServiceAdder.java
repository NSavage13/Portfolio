import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceAdder extends JFrame {
    private JFrame frame;
    private JTextField petIdField;
    private JButton addButton;

    public ServiceAdder() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        setTitle("Add Service");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel petIdLabel = new JLabel("Pet ID:");
        petIdField = new JTextField(10);

        add(petIdLabel);
        add(petIdField);
        
        addButton = new JButton("Add Service");
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addServiceToPet();
            }
        });

        populateServiceCheckboxes();
        add(panel);
        pack();
        setVisible(true);
    }

    private void populateServiceCheckboxes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        try (Connection connection = DriverManager.getConnection("")) {
            String sql = "SELECT ServiceName FROM Services";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String serviceName = resultSet.getString("ServiceName");
                    JCheckBox checkBox = new JCheckBox(serviceName);
                    panel.add(checkBox);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database Connection Problem:\n" + ex.getMessage());
        }

        add(panel);
    }

    private void addServiceToPet() {
        int petId = Integer.parseInt(petIdField.getText());

        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    String selectedService = checkBox.getText();
                    try (Connection connection = DriverManager.getConnection("")) {
                        String sql = "INSERT INTO VisitServices (VisitID, ServiceID) VALUES (?, " +
                                "(SELECT ServiceID FROM Services WHERE ServiceName = ?))";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                            
                            int visitID = 1;
                            preparedStatement.setInt(1, visitID);
                            preparedStatement.setString(2, selectedService);
                            preparedStatement.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Database Insert Problem:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(frame, "Services added to the pet successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new ServiceAdder();
    //         }
    //     });
    // }
}

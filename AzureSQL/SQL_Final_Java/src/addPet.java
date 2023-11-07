import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addPet extends JFrame {
    private JTextField petNameField;
    private JComboBox<String> petBreedComboBox;
    private JButton addButton;

    public addPet() {
        setTitle("Add New Pet");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel petNameLabel = new JLabel("Pet Name:");
        add(petNameLabel);

        petNameField = new JTextField(20);
        add(petNameField);

        JLabel petBreedLabel = new JLabel("Pet Breed:");
        add(petBreedLabel);

        petBreedComboBox = new JComboBox<>();
        petBreedComboBox.addItem("Dog");
        petBreedComboBox.addItem("Cat");
        petBreedComboBox.addItem("Bird");
        petBreedComboBox.addItem("Fish");
        petBreedComboBox.addItem("Other");
        add(petBreedComboBox);

        addButton = new JButton("Add Pet");
        add(addButton);
       // mainFrame.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adddPet();
            }
        });
    }

    private void adddPet() {
        String petName = petNameField.getText();
        String petBreed = petBreedComboBox.getSelectedItem().toString();

        if (petName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pet Name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection(";")) {
            String sql = "INSERT INTO Pets (OwnerID, Name, Breed) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // For simplicity, assume OwnerID is always 1 (you can adjust as needed)
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, petName);
                preparedStatement.setString(3, petBreed);
                preparedStatement.executeUpdate();

                petNameField.setText("");
                petBreedComboBox.setSelectedIndex(0);

                JOptionPane.showMessageDialog(this, "New pet added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Insert Problem:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

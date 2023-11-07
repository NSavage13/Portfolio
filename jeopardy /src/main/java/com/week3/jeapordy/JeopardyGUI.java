package com.week3.jeapordy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.*;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class JeopardyGUI extends JFrame {

    private JButton getQuestionButton;
    private JButton seeAnswerButton;
    private JTextArea questionField;
    private JTextArea answerField;
    private JLabel categoryLabel;
    JeopardyData jeopardyData = new JeopardyData();

    public JeopardyGUI() {
        setTitle("Jeopardy Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panel and set layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel titleLabel = new JLabel("Jeopardy Game");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Get Question Button
        getQuestionButton = new JButton("Get Question");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(getQuestionButton, gbc);

        // Category Label
        categoryLabel = new JLabel("Question Category");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(categoryLabel, gbc);

        // Question Field
        questionField = new JTextArea(5, 30);
        questionField.setEditable(false);
        questionField.setLineWrap(true); // Enable line wrap
        questionField.setWrapStyleWord(true);
        JScrollPane questionScrollPane = new JScrollPane(questionField);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(questionScrollPane, gbc);

        // See Answer Button
        seeAnswerButton = new JButton("See Answer");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(seeAnswerButton, gbc);

        // Answer Field
        answerField = new JTextArea(5, 30);
        answerField.setEditable(false);
        JScrollPane answerScrollPane = new JScrollPane(answerField);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(answerScrollPane, gbc);

        setContentPane(panel);

        // Action for Get Question Button
        getQuestionButton.addActionListener(e -> {
            String question = fetchQuestion();
            questionField.setText(question);
            categoryLabel.setText("Category: " + jeopardyData.getCategory().getTitle());
        });

        // Action for See Answer Button
        seeAnswerButton.addActionListener(e -> {
            String answer = jeopardyData.getAnswer();
            answerField.setText(answer);
        });
    }

    private String fetchQuestion() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://jservice.io/api/random");
    
            CloseableHttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
                
                Gson gson = new Gson();
                JeopardyData[] jeopardyDataArray = gson.fromJson(result.toString(), JeopardyData[].class);
                if (jeopardyDataArray.length > 0) {
                    jeopardyData = jeopardyDataArray[0];
                    answerField.setText("");
                    return jeopardyData.getQuestion();
                }
            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    

    public static void main(String[] args) {
        JeopardyGUI jeopardyGUI = new JeopardyGUI();
        jeopardyGUI.setVisible(true);
    }
}

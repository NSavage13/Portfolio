package com.nsavage.azureconnect;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private String baseUrl = "http://localhost:8080"; // Replace with your base URL
    private JTextArea textArea;

    public MainFrame() {
        setTitle("Select Option");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for top and bottom components

        JPanel topPanel = new JPanel(); // Create a separate panel for the buttons
        topPanel.setLayout(new FlowLayout());

        JButton ownersButton = new JButton("Show Owners");
        JButton petsButton = new JButton("Show Pets");

        textArea = new JTextArea(30, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        topPanel.add(ownersButton);
        topPanel.add(petsButton);

        panel.add(topPanel, BorderLayout.NORTH); // Place the buttons at the top
        panel.add(scrollPane, BorderLayout.CENTER); // Place the text area below the buttons

        new RestTemplate();

        add(panel);
        setVisible(true);

        petsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(performGetPets());
            }
        });

        ownersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(performGetOwners());
            }
        });
        
    }

    private String performGetOwners(){
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            String url = baseUrl + "/owners";
            HttpGet request = new HttpGet(url);

            try(CloseableHttpResponse response = httpClient.execute(request)){
                org.apache.http.HttpEntity entity = response.getEntity();
                if(entity != null){
                    String responseString = EntityUtils.toString(entity);
                    JSONArray jsonArray = new JSONArray(responseString);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        sb.append("Owner ID: " + jsonObject.getInt("ownerID") + "\n");
                        sb.append("Owner name: " + jsonObject.getString("firstName") + " " + jsonObject.getString("lastName") + "\n");
                        sb.append("Phone: " + jsonObject.getString("phone") + "\n");
                        sb.append("Email: " + jsonObject.getString("email") + "\n");
                        sb.append("Address: " + jsonObject.getString("address") + "\n");
                        sb.append("\n");
                    }
                    return sb.toString();
                }
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String performGetPets(){
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            String url = baseUrl + "/premiere";
            HttpGet request = new HttpGet(url);

            try(CloseableHttpResponse response = httpClient.execute(request)){
                org.apache.http.HttpEntity entity = response.getEntity();
                if(entity != null){
                    String responseString = EntityUtils.toString(entity);
                    JSONArray jsonArray = new JSONArray(responseString);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        sb.append("Owner ID: " + jsonObject.getInt("ownerId") + "\n");
                        sb.append("Pet ID: " + jsonObject.getInt("petId") + "\n");
                        sb.append("Name: " + jsonObject.getString("name") + "\n");
                        sb.append("Breed: " + jsonObject.getString("breed") + "\n");
                        sb.append("\n");
                    }
                    return sb.toString();
                }
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
    
}

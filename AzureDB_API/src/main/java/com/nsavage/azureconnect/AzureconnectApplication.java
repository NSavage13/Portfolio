package com.nsavage.azureconnect;

import javax.swing.JFrame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AzureconnectApplication extends JFrame {

	public static void main(String[] args) {
		SpringApplication.run(AzureconnectApplication.class, args);
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

	
}

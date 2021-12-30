package databaseManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class AdminLoginWin extends JFrame {
	
	//declare buttons, text fields and labels
	JButton submitB;
	JTextField tableNameF, userNameF;			
	JPasswordField passwordF;
	JLabel tableNameL, userNameL, passwordL;
	
	public AdminLoginWin () { //constructor method
		super("Login to Table");
		
		//initialise a JPanel, add it to the JFrame and set it to use a GridBagLayout.
		JPanel p0 = new JPanel();
		getContentPane().add(p0);
		p0.setLayout(new GridBagLayout());
		
		//set up constraints and padding for grid bag layout
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(5,5,5,5);
		cons.gridx = 0;
		cons.gridy = 0;
		
		//initialise components
		submitB = new JButton("Submit");
		tableNameF = new JTextField(15);
		userNameF = new JTextField(15);
		passwordF = new JPasswordField(15);
		tableNameL = new JLabel("Table Name:");
		userNameL = new JLabel("Admin Username:");
		passwordL = new JLabel("Admin Password:");
		
		//add components at relivant positions.
		p0.add(tableNameL,cons);
		cons.gridx = 1;
		p0.add(tableNameF,cons);
		cons.gridy = 1;
		cons.gridx = 0;
		p0.add(userNameL,cons);
		cons.gridx = 1;
		p0.add(userNameF,cons);
		cons.gridy = 2;
		cons.gridx = 0;
		p0.add(passwordL,cons);
		cons.gridx = 1;
		p0.add(passwordF,cons);
		cons.gridy = 3;
		cons.gridx = 0;
		p0.add(submitB,cons);
		
		submitB.addActionListener(new ButtonListener());
	}
	
	//this method closes the window
	private void close() { 
		this.dispose();
	}
	
	private class ButtonListener implements ActionListener { //action listener class

		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() == submitB) {
				boolean valid = false;
				SQL dbTool = new SQL();
				
				//test weather the inputted table exists and weather the relivant username and password corrisponds with the relivant admin username and password.
				try {
					if (dbTool.checkTable(tableNameF.getText())) {
						String[] admin = dbTool.selectById(1, tableNameF.getText());
						if (admin[0].equals(userNameF.getText()) && admin[1].equals(passwordF.getText())) {
							valid = true;
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				//open the main menu window if the login was accepted and inform the user if it was not accepted.
				if (valid == true) {
					WindowManager.mainMenu(tableNameF.getText());
				} else {
					JOptionPane.showMessageDialog(null, "There was an error logging you into the database, please try again.");
				}
				close();
			}
		}
	}
}

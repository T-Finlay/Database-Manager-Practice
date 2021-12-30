package databaseManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class NewTableWin extends JFrame {
	
	//declare buttons, text fields and labels
	JButton submitB;
	JTextField tableNameF, userNameF;			
	JPasswordField passwordF,confirmF;
	JLabel tableNameL, userNameL, passwordL, confirmL;
	
	public NewTableWin () { //constructor method
		super("New Table");
		
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
		confirmF = new JPasswordField(15);
		tableNameL = new JLabel("Table Name:");
		userNameL = new JLabel("Admin Username:");
		passwordL = new JLabel("Admin Password:");
		confirmL = new JLabel("Confirm Admin Password:");
		
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
		p0.add(confirmL,cons);
		cons.gridx = 1;
		p0.add(confirmF,cons);
		cons.gridy = 4;
		cons.gridx = 0;
		p0.add(submitB,cons);
		
		//add the actionListener to the submit button.
		submitB.addActionListener(new ButtonListener());
	}
	
	//this method closes the window
	private void close() { 
		this.dispose();
	}
	
	//this method is used to notify the user that one or more of their inputs are invalid via a pop up window.
	private void throwInvalid() {
		JOptionPane.showMessageDialog(null, "ERROR: INVALID INPUT(S).", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	private class ButtonListener implements ActionListener { //action listener class

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() == submitB) {
				
				//System.out.println(tableNameF.getText()); USED FOR TESTING 
				//this if statement makes sure that none of the if statements have been left empty and that 
				if ((tableNameF.getText().isEmpty()) || (userNameF.getText().isEmpty())   							
				|| (passwordF.getText().isEmpty()) || (!(passwordF.getText().equals(confirmF.getText())))) {
					throwInvalid();
					tableNameF.setText("");
					userNameF.setText("");
					passwordF.setText("");
					confirmF.setText("");
				} else {
					SQL dbTool = new SQL();
					try {
						
						//create a table with the given name if one does not already exist
						if (!dbTool.checkTable(tableNameF.getText())) {
							dbTool.createTable(tableNameF.getText(), userNameF.getText(), passwordF.getText());
							JOptionPane.showMessageDialog(null, "Table Created!", "Success", JOptionPane.INFORMATION_MESSAGE);
							
						//notify the user if a table with the given name already exists
						} else {
							JOptionPane.showMessageDialog(null, "ERROR: TABLE ALREADY EXISTS", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					close();
				}
			}
		}
	}
}

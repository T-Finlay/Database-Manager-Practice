package databaseManager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewUserWin extends JFrame {
	
	JTextField userNameF, passwordF;
	JLabel userNameL, passwordL;
	JTextArea instructions;
	JButton submitB,closeB;
	String tableName;
	
	public NewUserWin(String name) {
		super("Create New User");
		tableName = name;
		
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
		closeB = new JButton("Cancel");
		userNameF = new JTextField(15);
		passwordF = new JTextField(15);
		userNameL = new JLabel("New Username:");
		passwordL = new JLabel("New Password:");
		instructions = new JTextArea("Input the username and password of the new user into the following boxes.");
		
		//add components at relivant positions.
		p0.add(instructions,cons);
		instructions.setEditable(false);
		cons.gridy = 1;
		p0.add(userNameL,cons);
		cons.gridx = 1;
		p0.add(userNameF,cons);
		cons.gridx = 0;
		cons.gridy = 2;
		p0.add(passwordL,cons);
		cons.gridx = 1;
		p0.add(passwordF,cons);
		cons.gridy = 3;
		cons.gridx = 0;
		p0.add(submitB,cons);
		cons.gridx = 1;
		p0.add(closeB,cons);
		
		//add action Listeners
		submitB.addActionListener(new ButListener());
		closeB.addActionListener(new ButListener());
	}
	
	private void close() {
		//close this window
		this.dispose();
	}
	
	private class ButListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitB) {
				
				//use the SQL class and insert method to add a user with the inputted username and password
				SQL dbTool = new SQL();
				try {
					if (!(userNameF.getText().isEmpty() || passwordF.getText().isEmpty())) {
						dbTool.insert(tableName, userNameF.getText(), passwordF.getText());
						JOptionPane.showMessageDialog(null, "User Successfully Added!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					
					//if any fields are left empty, throw an error and notify the admin.
					JOptionPane.showMessageDialog(null, "ERROR: INVALID INPUT(S)" , "ERROR" , JOptionPane.ERROR_MESSAGE);
					userNameF.setText("");
					passwordF.setText("");
					
				} catch (SQLException e1) { //if there is an error, notify the user
					JOptionPane.showMessageDialog(null, "ERROR: USER COULD NOT BE ADDED.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				//return the user to the main menu
				WindowManager.mainMenu(tableName);
				close();
			} else if (e.getSource() == closeB) {
				
				//close the window and open the main menu if the close button is selected.
				WindowManager.mainMenu(tableName);
				close();
			}
		}
	}
}

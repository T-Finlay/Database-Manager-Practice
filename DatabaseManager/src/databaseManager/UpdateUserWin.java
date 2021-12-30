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


public class UpdateUserWin extends JFrame{

	JTextField userNameF, passwordF,idF;
	JLabel userNameL, passwordL,idL;
	JTextArea instructions;
	JButton submitB,closeB;
	String tableName;
	
	public UpdateUserWin(String name) {
		super("Update User");
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
		idF = new JTextField(15);
		userNameL = new JLabel("New Username:");
		passwordL = new JLabel("New Password:");
		idL = new JLabel("User ID:");
		instructions = new JTextArea("Input the new username and password into the following boxes.\n if any are empty they will be left unchanged.");
		
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
		p0.add(idL,cons);
		cons.gridx = 1;
		p0.add(idF,cons);
		cons.gridy = 4;
		cons.gridx = 0;
		p0.add(submitB,cons);
		cons.gridx = 1;
		p0.add(closeB,cons);
		
		closeB.addActionListener(new ButListener());
		submitB.addActionListener(new ButListener());
	}
	
	private void close() {
		//close this window
		this.dispose();
	}
	
	private boolean isPositiveInt(String a) {
		boolean isPosInt = false;
		try {
			int b = Integer.parseInt(a);
			if (b >= 0) {
				isPosInt = true;
			}
		} catch (Exception e) {
			isPosInt = false;
		}
		return isPosInt;
	}
	
	private class ButListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == closeB) {
				
				//close the window and return the user to the main menu
				WindowManager.mainMenu(tableName);
				close();
				
			} else if (e.getSource() == submitB) {
				//check if the id field contains a positive integer
				if (idF.getText().isEmpty() || !(isPositiveInt(idF.getText()))) {
					
					//if the id is not a posiitve integer, inform the user and reset the text field
					JOptionPane.showMessageDialog(null, "ERROR: IVALID ID" , "ERROR" , JOptionPane.ERROR_MESSAGE);
					idF.setText("");
					
				} else {
					
					//update the database and the relivant user, if no such user can be found, inform the user
					SQL dbTool = new SQL();
					try {
						dbTool.update(tableName, userNameF.getText(), passwordF.getText(), Integer.parseInt(idF.getText()));
						JOptionPane.showMessageDialog(null, "Success!", "Success!", JOptionPane.INFORMATION_MESSAGE);
						
						//close the window and return the user to the main menu
						WindowManager.mainMenu(tableName);
						close();
						
					} catch (Exception exe) {
						JOptionPane.showMessageDialog(null, "ERROR: ID not found", "ERROR", JOptionPane.ERROR_MESSAGE);
						idF.setText("");
					}
				}
			}
		}
	}
}

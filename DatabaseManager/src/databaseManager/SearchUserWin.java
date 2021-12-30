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

public class SearchUserWin extends JFrame {
	
	JTextField idF;
	JLabel userNameL, passwordL, idL, userNameOut , passwordOut;
	JTextArea instructions;
	JButton searchB,closeB;
	String tableName;
	
	public SearchUserWin (String tabName) {
		super("Search for user(s)");
		tableName = tabName;
		
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
		searchB = new JButton("Search");
		closeB = new JButton("Done");
		idF = new JTextField(15);
		userNameL = new JLabel("Username:");
		userNameOut = new JLabel("");
		passwordL = new JLabel("Password:");
		passwordOut = new JLabel("");
		idL = new JLabel("User ID:");
		instructions = new JTextArea("Input the ID you want to search by into the box below\n Then click search! click done when you're done.");		
		
		//add components at relivant positions on the GridBagLayout
		p0.add(instructions,cons);
		instructions.setEditable(false);
		cons.gridy = 1;
		p0.add(idL,cons);
		cons.gridx = 1;
		p0.add(idF,cons);
		cons.gridy = 2;
		cons.gridx = 0;
		p0.add(userNameL,cons);
		cons.gridx = 1;
		p0.add(userNameOut,cons);
		cons.gridy = 3;
		cons.gridx = 0;
		p0.add(passwordL,cons);
		cons.gridx = 1;
		p0.add(passwordOut,cons);
		cons.gridy = 4;
		cons.gridx = 0;
		p0.add(closeB,cons);
		cons.gridx = 1;
		p0.add(searchB,cons);
		
		//add the actionListeners to the relivant buttons
		closeB.addActionListener(new ButListener());
		searchB.addActionListener(new ButListener());
	}
	
	private void close() {
		//close this window
		this.dispose();
	}
	
	private class ButListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == closeB) {
				
				//close this window and return to the main menu
				WindowManager.mainMenu(tableName);
				close();
				
			} else if (e.getSource() == searchB) {
				SQL dbTool = new SQL();
				try {
					//use the SQL class' method to select the relivant user then output thier username and password to the admin
					String[] data = dbTool.selectById(Integer.parseInt(idF.getText()), tableName);
					userNameOut.setText(data[0]);
					passwordOut.setText(data[1]);
				} catch (Exception exe) {
					//if the admin input an invalid id then notify them and clear the text field so that they can try again.
					JOptionPane.showMessageDialog(null, "ERROR: NO USER COULD BE FOUND WITH THAT ID.", "ERROR",JOptionPane.ERROR_MESSAGE);
					idF.setText("");
				} 
			}
		}
	}
}

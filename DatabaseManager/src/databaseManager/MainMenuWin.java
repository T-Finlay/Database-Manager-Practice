package databaseManager;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainMenuWin extends JFrame{
	
	//declare window Components
	JButton but_createU,but_updateU,but_searchU,but_deleteU,but_logout;
	String tableName;
	
	//constructor
	public MainMenuWin() {
		
		//initialise window
		super("Main Menu");
		setLayout(new FlowLayout());
		
		//initialise buttons
		but_createU = new JButton("Create new user.");
		but_updateU = new JButton("Update user.");
		but_searchU = new JButton("Search users");
		but_deleteU = new JButton("Delete user.");
		but_logout = new JButton("Logout");
		
		//add buttons to window
		add(but_createU);
		add(but_updateU);
		add(but_searchU);
		add(but_deleteU);
		add(but_logout);
		
		//pack window
		pack();
		
		//add ActionListeners to buttons.
		but_logout.addActionListener(new AListener());
		but_createU.addActionListener(new AListener());
		but_updateU.addActionListener(new AListener());
		but_searchU.addActionListener(new AListener());
		but_deleteU.addActionListener(new AListener());
	}
	
	//this method is used to close the window.
	private void close() {
		this.dispose();
	}
	
	private class AListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == but_logout) {
				
				//display a pop up window asking the admin to confirm they want to logout
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout?", JOptionPane.OK_CANCEL_OPTION) == 0) {
					//close the window.
					close();
				}
				
			//all of the following if statements see which button has been pressed and opens the relivant window.
			} else if (e.getSource() == but_createU) {
				
				WindowManager.newUser(tableName);
				close();
			} else if (e.getSource() == but_updateU) {
				
				WindowManager.updateUser(tableName);
				close();
			} else if (e.getSource() == but_searchU) {
				
				WindowManager.searchUser(tableName);
				close();
			} else if (e.getSource() == but_deleteU) {
				
				WindowManager.deleteUser(tableName);
				close();
			}
		}
	}
}
